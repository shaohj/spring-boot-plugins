package net.ex.poi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import net.ex.poi.poiutils.ExcelUtils;
import net.ex.poi.poiutils.WorkbookUtils;
import net.ex.poi.tags.ITag;

import net.ex.poi.utils.PoiCommonUtils;
import org.apache.commons.beanutils.PropertyUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExcelParser {

	public static final String VALUED_DELIM = "${";

	public static final String VALUED_DELIM2 = "}";

	public static final String KEY_TAG = "#";

	public static Map tagPackageMap = new HashMap();

	private static Map tagMap = new HashMap();

	static {
		tagPackageMap.put(ITag.class.getPackage().getName(), ITag.class.getPackage().getName());
	}

	/**
	 * 根据模板边读边写导出数据。
	 * 一、翻译字符串思路如下
	 * 	  从模板开头依次往下读数据,cell数据是否翻译有3种情况
	 * 	  1.为null、空、常量，不翻译，拷贝数据及样式即可
	 * 	  2.为非标签的字符串表达式，翻译即可
	 * 	  3.为标签,完整解析该标签(递归处理)，解析完该标签后，跳过到标签结束处继续向下执行
	 * 	二、合并单元格的处理
	 * 	  暂时保留处理
	 * @param context : 存储动态数据的上下文
	 * @param outWb : 输出excel的工作簿
	 * @param inSheet : 模板文件Sheet
	 * @param outSheet : 输出文件Sheet
	 * @param inSheetStartRow : 模板文件起始数据行
	 * @param inSheetEndRow : 模板文件结束数据行
	 * @return int
	 * @author SHJ
	 * @since 2018/7/8 14:04
	 */
	public static void parse(Object context, SXSSFWorkbook outWb, Sheet inSheet, Sheet outSheet, int inSheetStartRow, int inSheetEndRow) {
		//当遇到标签时,处理标签导出数据后，读取和导出数据行数会变动，因此用下面变量标记处理。0为SkipNum，1为ShiftNum，2为break flag(值为1，标签为非法直接退出或为该标签在本行已处理完成，退出)
		int[] readShift = new int[] { 0, 0, 0 };
		int[] writeShift = new int[] { 0, 0, 0 };

		// 从模板开始读并动态处理数据
		int readRowNum = inSheetStartRow;
		int writeRowNum = inSheetStartRow;
		while (readRowNum <= inSheetEndRow) {
			// 读位置处理
			readRowNum += readShift[0] ;
			if (readRowNum > inSheetEndRow){
				break; //模板数据读取完成了
			}

			// 写位置处理
			writeRowNum += writeShift[0] + writeShift[1];

			//数据偏移量重置
			PoiCommonUtils.resetArray(readShift);
			PoiCommonUtils.resetArray(writeShift);

			// 设置当前导出的数据在第几行
			ExcelUtils.addValue(context, "currentRowNo", new Integer(writeRowNum + 1));

			// 读取模板Sheet中的一行数据,并进行转化写入导出文件中
			Row readRow = inSheet.getRow(readRowNum);
			Row writeRow = outSheet.createRow(writeRowNum); //注意有的表达式的列不会复制,比如foreach开头,需要手动再删除
			if(null != readRow){
				writeRow.setHeight(readRow.getHeight());
				writeRow.setHeightInPoints(readRow.getHeightInPoints());
			} else {  //模板中该行为null,因此不作处理
				readRowNum++;
				writeRowNum++;
				continue;
			}

			// 循环获取该行的所有Cell，对每个Cell的数据进行格式化
			for (short colnum = readRow.getFirstCellNum(); colnum <= readRow.getLastCellNum(); colnum++) {
				Cell inCell = readRow.getCell(colnum);
				Cell outCell = writeRow.createCell(colnum);
				if (null == inCell) { //模板中该Cell为null,因此不作处理
					continue;
				}
				//复制样式和值
				WorkbookUtils.copyCell(outWb, inCell, outCell);
				if (inCell.getCellTypeEnum() != CellType.STRING) { //模板中该Cell为非String值,因此不作处理
					continue;
				}
				// if the cell is null then continue
				String cellstr = inCell.getStringCellValue();
				if (null == cellstr || "".equals(cellstr)) { //模板中该Cell为空的String值,因此不作处理
					continue;
				}

				ITag tag = getTagClass(cellstr); //判断该表达式是否为标签
				if (null != tag) {
					//读取位置动态处理
					int[] locations =tag.getTagLocation(inSheet, readRow, inCell);
					if(locations[2] == 1){
						break;
					}

					//标签合法,则通过readShift和writeShift进行跳转
					readShift[0] = locations[1] -locations[0] + 1; //读取标签跳转到标签下面一行开始
					outSheet.removeRow(writeRow); //如foreach标签不应该再出现在导出结果中,移除掉

					writeShift = tag.parseTag(context, outWb, inSheet, outSheet, readRow, inCell);
					if (writeShift[2] == 1){ //本行Cell表达式处理完成
						break;
					}
				} else { //如果不为标签,将值转化并写入输出文件即可
					parseCell(context, inSheet, outSheet, readRow, inCell, outCell);
				}
			}

			// 正常流程时,读一行，写一行，都加一
			if(readShift[0] == 0){
				readRowNum++;
			}
			if(writeShift[0] + writeShift[1] == 0){
				writeRowNum++;
			}
		}

	}

	public static ITag getTagClass(String str) {
		String tagName = "";
		int keytag = str.indexOf(KEY_TAG);
		if (keytag < 0)
			return null;
		if (!(keytag < str.length() - 1))
			return null;
		String tagRight = str.substring(keytag + 1, str.length());
		if (tagRight.startsWith(KEY_TAG))
			return null;

		str = str.substring(str.indexOf(KEY_TAG) + KEY_TAG.length(), str.length());
		StringTokenizer st = new StringTokenizer(str, " ");
		if (st.hasMoreTokens()) {
			tagName = st.nextToken();
		}
		tagName = tagName.substring(0, 1).toUpperCase() + tagName.substring(1, tagName.length());
		tagName += "Tag";

		// find in tagMap first, if not exist, search in the package
		ITag tag = (ITag) tagMap.get(tagName);
		if (tag == null) {
			Iterator tagPackages = tagPackageMap.values().iterator();
			// seach the tag class
			while (tagPackages.hasNext() && null == tag) {
				String packageName = (String) tagPackages.next();
				try {
					Class clazz = Class.forName(packageName + "." + tagName);
					tag = (ITag) clazz.newInstance();
				} catch (Exception e) {
					tag = null;
				}
			}
			if (tag != null) {
				// find it, cache it
				tagMap.put(tagName, tag);
			}
		}
		return tag;
	}

	/**
	 * get the value from context by the expression
	 * 
	 * @param expr
	 * @param context
	 *            data object
	 * @return Object the value of the expr
	 */
	public static Object getValue(Object context, String expr) {
		Object value = null;
		try {
			value = PropertyUtils.getProperty(context, expr);
		} catch (Exception e) {
			return null;
		}
		return value;
	}

	/**
	 * parse the expression ${model[${index}]} only one ${} and startWith ${
	 * endWith }
	 * 
	 * @param context
	 *            data object
	 * @param expr
	 * 
	 * @return the value of the expr
	 */
	public static Object parseExpr(Object context, String expr) {

		int indexValued = expr.indexOf(VALUED_DELIM);
		int indexValued2 = expr.lastIndexOf(VALUED_DELIM2);

		Object value = null;

		if (indexValued == 0 && indexValued2 > 0) {
			String property = expr.substring(indexValued + VALUED_DELIM.length(), indexValued2);
			if (property.startsWith("!")) {
				property = property.substring(1, property.length());
			}

			if (property.indexOf(VALUED_DELIM) >= 0) {
				Object pro = parseStr(context, property);
				if (null != pro)
					property = pro.toString();
			}
			// get the value by expression
			value = getValue(context, property);
		} else if (indexValued > 0 && indexValued2 > 0) {
			value = parseStr(context, expr);
		}

		return value;
	}

	/**
	 * parse complex expression ${${}}aaa${}
	 * 
	 * @param context
	 * @param str
	 * @return value of the str
	 */
	public static Object parseStr(Object context, String str) {
		return parseStr(context, str, false);
	}

	/**
	 * parse complex expression ${${}}aaa${}
	 * 
	 * @param context
	 * @param str
	 * @param quot
	 *            string needs quotation or not
	 * @return value of the str
	 */
	public static Object parseStr(Object context, String str, boolean quot) {

		int exprCount = 0;
		int valueFrom = -1;
		int valueTo = -1;
		int valueCount = 0;
		int pos = 0;
		Object value = null;

		int indexValued = str.indexOf(VALUED_DELIM);
		int indexValued2 = str.lastIndexOf(VALUED_DELIM2);

		boolean bJustExpr = str.length() == indexValued2 + VALUED_DELIM2.length() - indexValued;

		while (pos < str.length()) {
			if (pos + VALUED_DELIM.length() <= str.length()) {
				if (VALUED_DELIM.equals(str.substring(pos, pos + VALUED_DELIM.length()))) {
					if (valueCount == 0) {
						valueFrom = pos;
					}
					valueCount++;
					pos = pos + VALUED_DELIM.length();
					continue;
				}
			}

			if (VALUED_DELIM2.equals(str.substring(pos, pos + VALUED_DELIM2.length()))) {
				valueCount--;
				if (valueCount == 0) {
					valueTo = pos;
					String expr = str.substring(valueFrom, valueTo + VALUED_DELIM2.length());
					value = parseExpr(context, expr);
					exprCount++;
					// replace the string
					StringBuffer sbuf = new StringBuffer(str);
					if (null != value) {
						String rep = value.toString();
						// need quotation
						if (quot) {
							rep = "\"" + rep + "\"";
						}
						sbuf.replace(valueFrom, valueTo + VALUED_DELIM2.length(), rep);
						pos += VALUED_DELIM2.length() + value.toString().length() - expr.length();
					} else {
						String rep = "";
						// need quotation
						if (quot) {
							rep = "\"" + rep + "\"";
						}
						sbuf.replace(valueFrom, valueTo + VALUED_DELIM2.length(), rep);
						pos += VALUED_DELIM2.length() + 0 - expr.length();
					}
					str = sbuf.toString();
					continue;
				} else {
					pos += VALUED_DELIM2.length();
					continue;
				}
			}
			pos++;
		}

		if (exprCount == 1 && bJustExpr) {
			if (null != value) {
				if (quot && "java.lang.String".equals(value.getClass().getName())) {
					return "\"" + value.toString() + "\"";
				}
				return value;
			}
			return value;
		} else {
			return str;
		}
	}

	public static void parseCell(Object context, Sheet fromSheet, Sheet toSheet, Row row, Cell readCell, Cell writeCell) {

		String str = readCell.getStringCellValue();
		if (null == str || "".equals(str)) {
			return;
		}

		if (str.indexOf(VALUED_DELIM) < 0)
			return;

		boolean bJustExpr = str.length() == (str.length() - str.lastIndexOf(VALUED_DELIM));
		boolean bMerge = "!".equals(str.substring(str.indexOf(VALUED_DELIM) + VALUED_DELIM.length(),
				str.indexOf(VALUED_DELIM) + VALUED_DELIM.length() + 1));

		if (str.indexOf(VALUED_DELIM) < 0)
			return;

		Object value = parseStr(context, str);

		// replace the cell
		if (null != value) {
			if (bJustExpr && "java.lang.Integer".equals(value.getClass().getName())) {
				writeCell.setCellValue(Double.parseDouble(value.toString()));
			} else if (bJustExpr && "java.lang.Double".equals(value.getClass().getName())) {
				writeCell.setCellValue(((Double) value).doubleValue());
			} else if (bJustExpr && "java.util.Date".equals(value.getClass().getName())) {
				writeCell.setCellValue((Date) value);
			} else if (bJustExpr && "java.lang.Boolean".equals(value.getClass().getName())) {
				writeCell.setCellValue(((Boolean) value).booleanValue());
			} else {
				writeCell.setCellValue(value.toString());
			}
		} else {
			writeCell.setCellValue("");
		}

		// merge the cell that has a "!" character at the expression
		if (row.getRowNum() - 1 >= fromSheet.getFirstRowNum() && bMerge) {
			Row lastRow = WorkbookUtils.getRow(row.getRowNum() - 1, fromSheet);
			Cell lastCell = WorkbookUtils.getCell(lastRow, readCell.getColumnIndex());
			boolean canMerge = false;
			if (lastCell.getCellTypeEnum() == readCell.getCellTypeEnum()) {
				switch (readCell.getCellTypeEnum()) {
				case STRING:
					canMerge = lastCell.getStringCellValue().equals(readCell.getStringCellValue());
					break;
				case BOOLEAN:
					canMerge = lastCell.getBooleanCellValue() == readCell.getBooleanCellValue();
					break;
				case NUMERIC:
					canMerge = lastCell.getNumericCellValue() == readCell.getNumericCellValue();
					break;
				default:
					break;
				}
			}
			if (canMerge) {
				CellRangeAddress nCr = new CellRangeAddress(lastRow.getRowNum(), lastCell.getColumnIndex(),
						row.getRowNum(), readCell.getColumnIndex());
				toSheet.addMergedRegion(nCr);
			}
		}

	}

	/**
	 * get Iterator from the object
	 * 
	 * @param collection
	 * @return Iterator of the object
	 */
	public static Iterator getIterator(Object collection) {
		Iterator iterator = null;
		if (collection.getClass().isArray()) {
			try {
				// If we're lucky, it is an array of objects
				// that we can iterate over with no copying
				iterator = Arrays.asList((Object[]) collection).iterator();
			} catch (ClassCastException e) {
				// Rats -- it is an array of primitives
				int length = Array.getLength(collection);
				ArrayList c = new ArrayList(length);
				for (int i = 0; i < length; i++) {
					c.add(Array.get(collection, i));
				}
				iterator = c.iterator();
			}
		} else if (collection instanceof Collection) {
			iterator = ((Collection) collection).iterator();
		} else if (collection instanceof Iterator) {
			iterator = (Iterator) collection;
		} else if (collection instanceof Map) {
			iterator = ((Map) collection).entrySet().iterator();
		}
		return iterator;
	}

	/**
	 * get Skip Num
	 * 
	 * @param tagstart
	 * @param tagend
	 * @return skip number
	 */
	public static int getSkipNum(int tagstart, int tagend) {
		return tagend - tagstart;
	}

	/**
	 * get shift Num
	 * 
	 * @param old_tagend
	 * @param tagstart
	 * @return shift number
	 */
	public static int getShiftNum(int old_tagend, int tagstart) {
		return tagstart - old_tagend;
	}
}
