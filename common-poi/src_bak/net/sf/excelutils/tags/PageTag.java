/*
 * Copyright 2003-2005 ExcelUtils http://excelutils.sourceforge.net
 * Created on 2005-6-22
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package net.sf.excelutils.tags;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * <p>
 * <b>PageTag </b> is a class which parse the #page tag Because a bug in POI,
 * you must place a split char in your sheet near the #page tag
 * </p>
 * 
 * @author rainsoft
 * @version $Revision: 1.8 $ $Date: 2005/10/28 01:19:51 $
 */
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
