package com.poi.template.excel.parse.cache.tags;

import java.util.Iterator;
import java.util.StringTokenizer;

import com.poi.template.excel.parse.cache.poiutils.CacheExcelUtils;
import com.poi.template.excel.parse.cache.poiutils.CacheWorkbookUtils;
import com.poi.template.excel.parse.cache.CacheExcelParser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * poi shiftRows方法移动时
 *  合并单元格格式丢失,添加处理.注意:foreach标签不要添加为合并单元格,以免标签删除后合并单元格对数据产生影响
 * 编  号：
 * 名  称：ForeachTag
 * 描  述：
 * 完成日期：2018/7/7 17:44
 * 编码作者：SHJ
 */
@SuppressWarnings({ "rawtypes", "deprecation" })
public class CacheForeachTag implements ICacheTag {

	public static final String KEY_FOREACH = "#foreach";

	public static final String KEY_END = "#end";

	public int[] parseTag(Object context, SXSSFWorkbook swb, Sheet fromSheet, Sheet toSheet, Row curRow, Cell curCell) {
		int forstart = curRow.getRowNum();
		int forend = -1;
		int forCount = 0;
		String foreach = "";
		boolean bFind = false;
		// 判断标签是否合理
		for (int rownum = forstart; rownum <= fromSheet.getLastRowNum(); rownum++) {
			Row row = fromSheet.getRow(rownum);
			if (null == row)
				continue;
			for (short colnum = row.getFirstCellNum(); colnum <= row.getLastCellNum(); colnum++) {
				Cell cell = row.getCell(colnum);
				if (null == cell)
					continue;
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					String cellstr = cell.getStringCellValue();

					ICacheTag tag = CacheExcelParser.getTagClass(cellstr);

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

		if (!bFind)
			return new int[] { 0, 0, 1 };

		String properties = "";
		String property = "";
		// parse the collection an object  处理数据：#foreach detail in ${list}
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
		Object collection = CacheExcelParser.parseStr(context, properties);
		if (null == collection) {
			return new int[] { 0, 0, 1 };
		}
		// get the iterator of collection
		Iterator iterator = CacheExcelParser.getIterator(collection);

		// iterator
		int shiftNum = forend - forstart - 1;
		// set the start row number
		CacheExcelUtils.addValue(context, property + "StartRowNo", new Integer(forstart + 1));

		int old_forstart = forstart;
		int old_forend = forend;
		int propertyId = 0;
		int shift = 0;

		if (null != iterator) {
			// Todo 一维数组只支持foreach中只有一行,需要改为二维数组就支持多行了
			Row fromRow = fromSheet.getRow(old_forstart + 1);
			int colsNum = fromRow.getLastCellNum();

			CellStyle[] fromCellStyles = new CellStyle[colsNum];
			for (int i = fromRow.getFirstCellNum(); i < colsNum && i >= 0; i++) {
				Cell fromCell = CacheWorkbookUtils.getCell(fromRow, i);
				CellStyle cs = swb.createCellStyle();
				cs.cloneStyleFrom(fromCell.getCellStyle());
				fromCellStyles[i] = cs;
			}

			while (iterator.hasNext()) {
				Object obj = iterator.next();

				CacheExcelUtils.addValue(context, property, obj);
				// Iterator ID
				CacheExcelUtils.addValue(context, property + "Id", new Integer(propertyId));
				// Index start with 1
				CacheExcelUtils.addValue(context, property + "Index", new Integer(propertyId + 1));

				// copy the body fo #foreach #end block
				int writeRowNum = forstart + shiftNum;
				CacheWorkbookUtils.copyRow2(context, swb, fromSheet, toSheet, fromRow, fromCellStyles, writeRowNum, forstart, shiftNum);

				forstart += shiftNum + shift;
				forend += shiftNum + shift;
				propertyId++;
			}
		}
		// set the end row number
		CacheExcelUtils.addValue(context, property + "EndRowNo", new Integer(forstart));

		return new int[] { CacheExcelParser.getSkipNum(forstart, forend), CacheExcelParser.getShiftNum(old_forend, forstart), 1 };
	}

	public String getTagName() {
		return KEY_FOREACH;
	}

	@Override
	public int[] getTagLocation(Sheet sheet, Row curRow, Cell curCell) {
		int forstart = curRow.getRowNum();
		int forend = -1;
		int forCount = 0;
		boolean bFind = false;
		// 判断标签是否合理
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

					ICacheTag tag = CacheExcelParser.getTagClass(cellstr);

					if (null != tag) {
						if (tag.hasEndTag()) {
							if (0 == forCount) {
								forstart = rownum;
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

		if (!bFind) { //标签为非法标签
			return new int[]{0, 0, 1};
		}

		return new int[] {forstart, forend, 0};
	}

	public boolean hasEndTag() {
		return true;
	}
}
