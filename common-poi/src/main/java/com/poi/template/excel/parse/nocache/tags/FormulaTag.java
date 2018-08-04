package com.poi.template.excel.parse.nocache.tags;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.poi.template.excel.parse.nocache.ExcelParser;

public class FormulaTag implements ITag {

	public static final String KEY_FORMULA = "#formula";

	public int[] parseTag(Object context, Sheet sheet, Row curRow, Cell curCell) {

		String cellstr = curCell.getStringCellValue();
		if (null == cellstr || "".equals(cellstr)) {
			return new int[] { 0, 0, 0 };
		}

		cellstr = cellstr.substring(KEY_FORMULA.length()).trim();

		Object formula = ExcelParser.parseStr(context, cellstr);

		if (null != formula) {
			curCell.setCellFormula(formula.toString());
		}

		return new int[] { 0, 0, 0 };
	}

	public boolean hasEndTag() {
		return false;
	}

	public String getTagName() {
		return KEY_FORMULA;
	}

}
