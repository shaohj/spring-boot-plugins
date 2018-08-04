package com.poi.template.excel.parse.nocache.tags;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public interface ITag {

	/**
	 * parse the tag
	 * 
	 * @param context
	 *            data object
	 * @param sheet
	 *            excel sheet
	 * @param curRow
	 *            excel row
	 * @param curCell
	 *            excel cell
	 * @return int[] {skip number, shift number, break flag}
	 */
	public int[] parseTag(Object context, Sheet sheet, Row curRow, Cell curCell);

	/**
	 * tag has #end flag
	 * 
	 * @return boolean
	 */
	public boolean hasEndTag();

	/**
	 * get the tag name
	 * 
	 * @return str
	 */
	public String getTagName();
}
