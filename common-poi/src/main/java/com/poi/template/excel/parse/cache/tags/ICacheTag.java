package com.poi.template.excel.parse.cache.tags;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public interface ICacheTag {

	public int[] parseTag(Object context, SXSSFWorkbook swb, Sheet sheet, Sheet newSheet, Row curRow, Cell curCell);

	public boolean hasEndTag();

	public String getTagName();

	/**
	 * 获取标签的起始和结束在Sheet中的位置
	 * @param sheet :
	 * @param curRow : 当前行
	 * @param curCell : 当前Cell
	 * @return int[] 索引0为起始行，1为结束行，2为break flag(值为1，标签非法直接退出)
	 * @author SHJ
	 * @since 2018/7/8 14:50
	 */
	public int[] getTagLocation(Sheet sheet, Row curRow, Cell curCell);

}
