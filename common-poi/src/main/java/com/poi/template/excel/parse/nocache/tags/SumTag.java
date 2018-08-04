package com.poi.template.excel.parse.nocache.tags;

import java.util.Iterator;
import java.util.StringTokenizer;

import com.poi.template.excel.parse.nocache.ExcelParser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
@SuppressWarnings({ "rawtypes" })
public class SumTag implements ITag {

	public static final String KEY_SUM = "#sum";

	public int[] parseTag(Object context, Sheet sheet, Row curRow, Cell curCell) {
		String cellstr = curCell.getStringCellValue();
		if (null == cellstr || "".equals(cellstr)) {
			return new int[] { 0, 0, 0 };
		}

		String property = "";
		String collectionName = "";
		String whereName = "";
		String whereStr = "";
		boolean bEquals = true;
		String hasStr = cellstr.substring(0, cellstr.indexOf(getTagName()));
		cellstr = cellstr.substring(cellstr.indexOf(getTagName()), cellstr.length());
		cellstr = cellstr.replaceAll("=", " = ");
		StringTokenizer st = new StringTokenizer(cellstr, " ");
		int pos = 0;
		while (st.hasMoreTokens()) {
			String str = st.nextToken();
			if (pos == 1) {
				property = str;
			}
			if (pos == 3) {
				collectionName = str;
			}
			if (pos == 5) {
				whereName = str;
			}
			if (pos == 6) {
				bEquals = "=".equals(str.trim());
			}
			if (pos == 7) {
				whereStr = str;
			}
			pos++;
		}

		double sum = 0;
		if (!"".equals(collectionName) && !"".equals(property)) {
			Object collection = ExcelParser.parseStr(context, collectionName);
			if (null == collection) {
				return new int[] { 0, 0, 0 };
			}
			// get the iterator of collection
			Iterator iterator = ExcelParser.getIterator(collection);
			// iterator for sum
			while (iterator.hasNext()) {
				Object model = iterator.next();
				if (null != model) {
					Object value = ExcelParser.getValue(model, property);
					if (null != value) {
						// filter when
						if (!"".equals(whereName) && !"".equals(whereStr)) {
							Object where = ExcelParser.getValue(model, whereName);
							Object whereValue = null;
							if (whereStr.startsWith(ExcelParser.VALUED_DELIM)) {
								whereValue = ExcelParser.parseStr(context, whereStr);
							}
							if (null == whereValue)
								whereValue = whereStr;

							if (null != where) {
								if (bEquals) {
									if (!where.toString().equals(whereValue.toString())) {
										continue;
									}
								} else {
									if (where.toString().indexOf(whereValue.toString()) < 0) {
										continue;
									}
								}
							}
						}

						// sum the value
						if ("java.lang.Integer".equals(value.getClass().getName())
								|| "java.lang.Double".equals(value.getClass().getName())) {
							sum += Double.parseDouble(value.toString());
						}
					}
				}
			}
		}
		if (!"".equals(hasStr)) {
			curCell.setCellValue(hasStr + sum);
		} else {
			curCell.setCellValue(sum);
		}
		return new int[] { 0, 0, 0 };
	}

	public String getTagName() {
		return KEY_SUM;
	}

	public boolean hasEndTag() {
		return false;
	}
}
