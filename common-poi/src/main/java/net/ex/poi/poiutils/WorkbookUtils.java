package net.ex.poi.poiutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.ex.poi.ExcelException;
import net.ex.poi.utils.IOUtils;
import net.ex.poi.utils.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.util.CellRangeAddress;

@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
public class WorkbookUtils {

	public WorkbookUtils() {}

	public static Workbook openWorkbookByProPath(String fileName) throws ExcelException {
		InputStream in = null;
		Workbook wb = null;
		try {
			in = WorkbookUtils.class.getClassLoader().getResourceAsStream(fileName);
			String fileSuffix = StringUtils.getStringSuffix(fileName);
			wb = openWorkbook(in, fileSuffix);
		} catch (Exception e) {
			throw new ExcelException("File" + fileName + "not found" + e.getMessage());
		} finally {
			IOUtils.close(in);
		}
		return wb;
	}

	public static Workbook openWorkbookByAbsolute(String fileName) throws ExcelException {
		InputStream in = null;
		Workbook wb = null;
		try {
			in = new FileInputStream(fileName);
			String fileSuffix = StringUtils.getStringSuffix(fileName);
			wb = openWorkbook(in, fileSuffix);
		} catch (Exception e) {
			throw new ExcelException("File" + fileName + "not found" + e.getMessage());
		} finally {
			IOUtils.close(in);
		}
		return wb;
	}

	private static Workbook openWorkbook(InputStream in, String fileSuffix) throws IOException {
		Workbook wb = null;
		if ("xls".equals(fileSuffix)) {
			wb = new HSSFWorkbook(in);
		} else if ("xlsx".equals(fileSuffix)) {
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}

	public static Row getRow(int rowCounter, Sheet sheet) {
		Row row = sheet.getRow((short) rowCounter);
		if (row == null) {
			row = sheet.createRow((short) rowCounter);
		}
		return row;
	}

	public static Cell getCell(Row row, int column) {
		Cell cell = row.getCell((short) column);

		if (cell == null) {
			cell = row.createCell((short) column);
		}
		return cell;
	}

	public static void copyRow(Sheet sheet, int from, int to, int count) {
		for (int rownum = from; rownum < from + count; rownum++) {
			Row fromRow = sheet.getRow(rownum);
			if (null == fromRow)
				return;
			int colsNum = fromRow.getLastCellNum();
			Row toRow = getRow(to + rownum - from, sheet);

			toRow.setHeight(fromRow.getHeight());
			toRow.setHeightInPoints(fromRow.getHeightInPoints());

			for (int i = fromRow.getFirstCellNum(); i <= colsNum && i >= 0; i++) {
				Cell fromCell = getCell(fromRow, i);
				Cell toCell = getCell(toRow, i);
				toCell.setCellStyle(fromCell.getCellStyle());
				toCell.setCellType(fromCell.getCellTypeEnum());
				switch (fromCell.getCellTypeEnum()) {
				case BOOLEAN:
					toCell.setCellValue(fromCell.getBooleanCellValue());
					break;
				case FORMULA:
					toCell.setCellFormula(fromCell.getCellFormula());
					break;
				case NUMERIC:
					toCell.setCellValue(fromCell.getNumericCellValue());
					break;
				case STRING:
					toCell.setCellValue(fromCell.getStringCellValue());
					break;
				default:
				}
			}
		}

		// copy merged region
		List shiftedRegions = new ArrayList();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress r = sheet.getMergedRegion(i);
			if (r.getFirstRow() >= from && r.getLastRow() < from + count) {
				CellRangeAddress nCr = new CellRangeAddress(r.getFirstRow() + to - from, r.getLastRow() + to - from,
						r.getFirstColumn(), r.getLastColumn());
				shiftedRegions.add(nCr);
			}
		}

		// readd so it doesn't get shifted again
		Iterator iterator = shiftedRegions.iterator();
		while (iterator.hasNext()) {
			CellRangeAddress region = (CellRangeAddress) iterator.next();
			sheet.addMergedRegion(region);
		}
	}

}
