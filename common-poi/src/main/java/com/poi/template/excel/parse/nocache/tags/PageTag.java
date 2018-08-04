package com.poi.template.excel.parse.nocache.tags;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class PageTag implements ITag {
	public static final String KEY_PAGE = "#page";

	public int[] parseTag(Object context, Sheet sheet, Row curRow, Cell curCell) {
		sheet.setRowBreak(curRow.getRowNum());
		curCell.setCellValue("");
		return new int[] { 0, 0, 0 };
	}

	public String getTagName() {
		return KEY_PAGE;
	}

	public boolean hasEndTag() {
		return false;
	}
}
