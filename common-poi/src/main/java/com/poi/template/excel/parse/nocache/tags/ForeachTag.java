package com.poi.template.excel.parse.nocache.tags;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.poi.template.excel.parse.nocache.ExcelParser;
import com.poi.template.excel.parse.nocache.ExcelUtils;
import com.poi.template.excel.parse.nocache.WorkbookUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 2016/6/6 poi shiftRows方法移动时
 * 合并单元格格式丢失,添加处理.注意:foreach标签不要添加为合并单元格,以免标签删除后合并单元格对数据产生影响
 * 
 * @author ShaoHj
 *
 */
@SuppressWarnings({ "rawtypes", "deprecation" })
public class ForeachTag implements ITag {

	public static int i = 0; // 调试使用

	public static final String KEY_FOREACH = "#foreach";

	public static final String KEY_END = "#end";

	public int[] parseTag(Object context, Sheet sheet, Row curRow, Cell curCell) {
		// System.out.println("转换前所有合并单元格：");
		//
		// for (int i=0; i<sheet.getNumMergedRegions(); i++) {
		// CellRangeAddress r = sheet.getMergedRegion(i);
		// System.out.println("firstRow="+r.getFirstRow()+",lastRow="+r.getLastRow()+",firstCol="+r.getFirstColumn()+",lastCol="+r.getLastColumn());
		// }

		int forstart = curRow.getRowNum();
		int forend = -1;
		int forCount = 0;
		String foreach = "";
		boolean bFind = false;
		for (int rownum = forstart; rownum <= sheet.getLastRowNum(); rownum++) {
			Row row = sheet.getRow(rownum);
			if (null == row)
				continue;
			for (short colnum = row.getFirstCellNum(); colnum <= row.getLastCellNum(); colnum++) {
				Cell cell = row.getCell(colnum);
				if (null == cell)
					continue;
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					String cellstr = cell.getStringCellValue();

					// get the tag instance for the cellstr
					ITag tag = ExcelParser.getTagClass(cellstr);

					if (null != tag) {
						if (tag.hasEndTag()) {
							if (0 == forCount) {
								forstart = rownum;
								foreach = cellstr;
							}
							forCount++;
							break;
						}
					}
					if (cellstr.startsWith(KEY_END)) {
						forend = rownum;
						forCount--;
						if (forstart >= 0 && forend >= 0 && forend > forstart && forCount == 0) {
							bFind = true;
						}
						break;
					}
				}
			}
			if (bFind)
				break;
		}

		// System.out.println("parseTag start");
		// System.out.println("**_forstart="+forstart+",forend="+forend);

		// 第二次调试禁止开关
		// i++;
		// if(i>=2){
		// return new int[] { 0, 0, 1 };
		// }
		if (!bFind)
			return new int[] { 0, 0, 1 };

		String properties = "";
		String property = "";
		// parse the collection an object
		StringTokenizer st = new StringTokenizer(foreach, " ");
		int pos = 0;
		while (st.hasMoreTokens()) {
			String str = st.nextToken();
			if (pos == 1) {
				property = str;
			}
			if (pos == 3) {
				properties = str;
			}
			pos++;
		}
		// get collection
		Object collection = ExcelParser.parseStr(context, properties);
		if (null == collection) {
			return new int[] { 0, 0, 1 };
		}
		// get the iterator of collection
		Iterator iterator = ExcelParser.getIterator(collection);

		// iterator
		int shiftNum = forend - forstart - 1;
		// set the start row number
		ExcelUtils.addValue(context, property + "StartRowNo", new Integer(forstart + 1));

		int old_forstart = forstart;
		int old_forend = forend;
		int propertyId = 0;
		int shift = 0;

		/**
		 * 当含有多个tag时,如多个#foreach标签,shift移动的标签会丢失,特殊进行处理 ,2个步骤.步骤1
		 */
		List<CellRangeAddress> originMerged = sheet.getMergedRegions();
		if (null != iterator) {

			while (iterator.hasNext()) {
				Object obj = iterator.next();

				ExcelUtils.addValue(context, property, obj);
				// Iterator ID
				ExcelUtils.addValue(context, property + "Id", new Integer(propertyId));
				// Index start with 1
				ExcelUtils.addValue(context, property + "Index", new Integer(propertyId + 1));

				// shift the #foreach #end block
				sheet.shiftRows(forstart, sheet.getLastRowNum(), shiftNum, true, true);

				// break;
				// copy the body fo #foreach #end block
				WorkbookUtils.copyRow(sheet, forstart + shiftNum + 1, forstart, shiftNum);
				// // parse
				shift = ExcelParser.parse(context, sheet, forstart, forstart + shiftNum - 1);
				//
				forstart += shiftNum + shift;
				forend += shiftNum + shift;
				propertyId++;
			}
		}
		// set the end row number
		ExcelUtils.addValue(context, property + "EndRowNo", new Integer(forstart));
		// delete #foreach #end block
		 for (int rownum = forstart; rownum <= forend; rownum++) {
		 sheet.removeRow(WorkbookUtils.getRow(rownum, sheet));
		 }
		//
		// System.out.println("合并单元格预处理");
		// System.out.println("forstart="+forstart+",forend="+forend);

		// System.out.println("转换后所有合并单元格：");
		// for (int i=0; i<sheet.getNumMergedRegions(); i++) {
		// CellRangeAddress r = sheet.getMergedRegion(i);
		// System.out.println("firstRow="+r.getFirstRow()+",lastRow="+r.getLastRow()+",firstCol="+r.getFirstColumn()+",lastCol="+r.getLastRow());
		// }
		// remove merged region in forstart & forend
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress r = sheet.getMergedRegion(i);
			if (r.getFirstRow() >= forstart && r.getLastRow() <= forend) {
				// System.out.println("合并单元格处理");
				// System.out.println("forstart="+forstart+",forend="+forend);
				sheet.removeMergedRegion(i);
				// we have to back up now since we removed one
				i = i - 1;
			}
		}

		sheet.shiftRows(forend + 1, sheet.getLastRowNum(), -(forend - forstart + 1), true, true);

		/**
		 * shift移动的标签会丢失,特殊进行处理.2个步骤.步骤2
		 */
		int allShiftNum = forstart - old_forstart - (old_forend - old_forstart + 1); // old_forend-old_forstart+1为forecah语句长度
		for (CellRangeAddress cellRangeAddress : originMerged) {
			// 这里的8是插入行的index，表示这行之后才重新合并
			if (cellRangeAddress.getFirstRow() >= old_forend) {
				// 你插入了几行就加几，我这里插入了一行,加1
				int firstRow = cellRangeAddress.getFirstRow() + allShiftNum;
				CellRangeAddress newCellRangeAddress = new CellRangeAddress(firstRow,
						(firstRow + (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())),
						cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
				try {
					sheet.addMergedRegion(newCellRangeAddress);
				} catch (Exception e) {

				}
			}
		}

		return new int[] { ExcelParser.getSkipNum(forstart, forend), ExcelParser.getShiftNum(old_forend, forstart), 1 };
	}

	public String getTagName() {
		return KEY_FOREACH;
	}

	public boolean hasEndTag() {
		return true;
	}
}
