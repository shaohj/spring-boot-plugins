package net.ex.poi.poiutils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.ex.poi.ExcelException;
import net.ex.poi.ExcelParser;
import net.ex.poi.utils.IOUtils;
import net.ex.poi.utils.POIUtils;
import net.ex.poi.utils.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.util.CellRangeAddress;

@SuppressWarnings({ "rawtypes", "unchecked", "resource" })
public class WorkbookUtils {

	public WorkbookUtils() {}

	public static XSSFWorkbook openWorkbookByProPath(String fileName) throws ExcelException {
		InputStream in = null;
		XSSFWorkbook wb = null;
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

	public static XSSFWorkbook openWorkbookByAbsolute(String fileName) throws ExcelException {
		InputStream in = null;
		XSSFWorkbook wb = null;
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

	private static XSSFWorkbook openWorkbook(InputStream in, String fileSuffix) throws Exception {
		XSSFWorkbook wb = null;
		if ("xlsx".equals(fileSuffix)) {
			wb = new XSSFWorkbook(in);
		} else{
			throw new IllegalArgumentException("poi缓存导出只支持xlsx,程序同步,支持支xlsx导出");
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
				copyCell(fromCell, toCell);
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

	public static void copyRow(SXSSFWorkbook swb, Sheet fromSheet, Sheet toSheet, Row fromRow, CellStyle[] fromCellStyles, int from, int to, int count) {
		for (int rownum = from; rownum < from + count; rownum++) {
			int colsNum = fromRow.getLastCellNum();
			int shoortNum =  to + rownum - from;
			Row toRow = toSheet.createRow(shoortNum);

			toRow.setHeight(fromRow.getHeight());
			toRow.setHeightInPoints(fromRow.getHeightInPoints());

			for (int i = fromRow.getFirstCellNum(); i < colsNum && i >= 0; i++) {
				Cell fromCell = getCell(fromRow, i);
				Cell toCell = getCell(toRow, i);
				CellStyle cs = fromCellStyles[i];
				copyCell(fromCell, toCell, cs);
			}
		}

		// copy merged region
		List shiftedRegions = new ArrayList();
		for (int i = 0; i < fromSheet.getNumMergedRegions(); i++) {
			CellRangeAddress r = fromSheet.getMergedRegion(i);
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
			toSheet.addMergedRegion(region);
		}
	}

	public static void copyRow2(Object context, SXSSFWorkbook swb, Sheet fromSheet, Sheet toSheet, Row fromRow, CellStyle[] fromCellStyles, int from, int to, int count) {
		for (int rownum = from; rownum < from + count; rownum++) {
			int colsNum = fromRow.getLastCellNum();
			int shoortNum =  to + rownum - from;
			Row toRow = toSheet.createRow(shoortNum);

			toRow.setHeight(fromRow.getHeight());
			toRow.setHeightInPoints(fromRow.getHeightInPoints());

			for (int i = fromRow.getFirstCellNum(); i < colsNum && i >= 0; i++) {
				Cell fromCell = getCell(fromRow, i);
				Cell toCell = getCell(toRow, i);
				CellStyle cs = fromCellStyles[i];
				copyCell(fromCell, toCell, cs);

				ExcelParser.parseCell(context, fromSheet, toSheet, fromRow, fromCell, toCell);
			}
		}

		// copy merged region
		List shiftedRegions = new ArrayList();
		for (int i = 0; i < fromSheet.getNumMergedRegions(); i++) {
			CellRangeAddress r = fromSheet.getMergedRegion(i);
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
			toSheet.addMergedRegion(region);
		}
	}

	public static void copyCell(Cell fromCell, Cell toCell) {
		copyCell(fromCell, toCell, fromCell.getCellStyle());
	}

	public static void copyCell(Workbook toWb, Cell fromCell, Cell toCell) {
		CellStyle cs = toWb.createCellStyle();
		cs.cloneStyleFrom(fromCell.getCellStyle());

		copyCell(fromCell, toCell, cs);
	}

	public static void copyCell(Cell fromCell, Cell toCell, CellStyle cs) {
		toCell.setCellStyle(cs);
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
