package net.ex.poi.poiutils;

import java.io.OutputStream;
import java.util.Map;

import net.ex.poi.ExcelException;
import net.ex.poi.ExcelParser;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExcelUtils {

	static ThreadLocal context = new ThreadLocal();

	/**
	 * 初始化workbook配置
	 * @param wb :
	 * @return
	 * @author SHJ
	 * @since 2018/7/7 18:35
	 */
	public static void initExcelProperties(Workbook wb) {
		wb.setForceFormulaRecalculation(true); // 当整个workbook打开时,刷新工作表
	}

	public static void export(String fileName, OutputStream out) throws ExcelException {
		export(fileName, getContext(), out);
	}

	public static void export(String fileName, Object context, OutputStream out) throws ExcelException {
		try {
			Workbook wb = WorkbookUtils.openWorkbookByProPath(fileName);
			initExcelProperties(wb);

			parseWorkbook(context, wb);

			if(wb instanceof XSSFWorkbook){
				XSSFWorkbook xwb = (XSSFWorkbook) wb;
				SXSSFWorkbook swb = new SXSSFWorkbook(xwb,10);
				swb.write(out);
			} else {
				wb.write(out);
			}
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	public static void parseWorkbook(Object context, Workbook wb) throws ExcelException {
		try {
			int sheetCount = wb.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
				Sheet sheet = wb.getSheetAt(sheetIndex);
				parseSheet(context, sheet);
			}
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	public static void parseSheet(Object context, Sheet sheet) throws ExcelException {
		try {
			ExcelParser.parse(context, sheet, sheet.getFirstRowNum(), sheet.getLastRowNum());
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		} finally {
			ExcelUtils.context.set(null);
		}
	}

	public static void addValue(Object context, String key, Object value) {
		if (context instanceof DynaBean) {
			((DynaBean) context).set(key, value);
		} else if (context instanceof Map) {
			((Map) context).put(key, value);
		}
	}

	public static void addValue(String key, Object value) {
		getContext().set(key, value);
	}

	private static DynaBean getContext() {
		DynaBean ctx = (DynaBean) context.get();
		if (null == ctx) {
			ctx = new LazyDynaBean();
			setContext(ctx);
		}
		return ctx;
	}

	private static void setContext(DynaBean ctx) {
		context.set(ctx);
	}

}