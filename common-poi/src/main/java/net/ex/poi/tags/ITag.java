package net.ex.poi.tags;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public interface ITag {

	public int[] parseTag(Object context, Sheet sheet, Row curRow, Cell curCell);

	public boolean hasEndTag();

	public String getTagName();
}
