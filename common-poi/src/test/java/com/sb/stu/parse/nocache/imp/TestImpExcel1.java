package com.sb.stu.parse.nocache.imp;

import java.util.Iterator;

import com.poi.template.excel.exception.ExcelException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.poi.template.excel.parse.nocache.WorkbookUtils;

public class TestImpExcel1 {

	public static final String IMP_FILE_PATH = "\\xlsx\\demo_template_exp.xlsx";

	public static void main(String[] args) {
		try {
			Workbook wb = WorkbookUtils.openWorkbook(IMP_FILE_PATH);
			
			Sheet sheet = wb.getSheetAt(0);
			
			// Iterate over each row in the sheet
			Iterator<Row> rows = sheet.rowIterator();
			while (rows.hasNext()) {
				Row row = rows.next();
				System.out.println("Row #" + row.getRowNum());
				// Iterate over each cell in the row and print out the cell"s
				// content
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					System.out.println("Cell #" + cell.getColumnIndex());
					switch (cell.getCellTypeEnum()) {
					case NUMERIC:
						System.out.println(cell.getNumericCellValue());
						break;
					case STRING:
						System.out.println(cell.getStringCellValue());
						break;
					case BOOLEAN:
						System.out.println(cell.getBooleanCellValue());
						break;
					case FORMULA:
						System.out.println(cell.getCellFormula());
						break;
					case BLANK:
						System.out.println("BLANK");
						break;
					default:
						System.out.println("unsuported sell type");
						break;
					}
				}
			}
		} catch (ExcelException e) {
			e.printStackTrace();
		}
	}

}
