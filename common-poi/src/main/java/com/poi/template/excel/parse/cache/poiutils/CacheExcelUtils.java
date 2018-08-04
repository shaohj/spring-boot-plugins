package com.poi.template.excel.parse.cache.poiutils;

import java.io.OutputStream;
import java.util.Map;

import com.poi.template.excel.exception.ExcelException;
import com.poi.template.excel.parse.cache.CacheExcelParser;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CacheExcelUtils {

	private static final Logger logger = LoggerFactory.getLogger(CacheExcelUtils.class);

	static ThreadLocal context = new ThreadLocal();

	/**
	 * 初始化workbook配置
	 * @param inWb :
	 * @return
	 * @author SHJ
	 * @since 2018/7/7 18:35
	 */
	public static void initExcelProperties(XSSFWorkbook inWb) {
		inWb.setForceFormulaRecalculation(true); // 当整个workbook打开时,刷新工作表
	}

	/**
	 * 导出文件
	 * @param tempFileName : 模板文件名
	 * @param out : 输出文件流
	 * @return void
	 * @author SHJ
	 * @since 2018/7/8 13:41
	 */
	public static void export(String tempFileName, OutputStream out) throws ExcelException {
		export(tempFileName, getContext(), out);
	}

	/**
	 * 导出文件
	 * @param tempFileName : 模板文件名
	 * @param context : context
	 * @param out : 输出文件流
	 * @return void
	 * @author SHJ
	 * @since 2018/7/8 13:41
	 */
	public static void export(String tempFileName, Object context, OutputStream out) throws ExcelException {
		try {
			//打开工作簿 并进行初始化
			XSSFWorkbook inWb = CacheWorkbookUtils.openWorkbookByProPath(tempFileName);
			initExcelProperties(inWb);

			//创建缓存的输出文件工作簿
			SXSSFWorkbook outWb = new SXSSFWorkbook(100);

			//根据模板边读边写导出数据
			parseWorkbook(context, inWb, outWb);

			//输出文件并清理导出的临时缓存文件
			outWb.write(out);
			outWb.dispose();
		} catch (Exception e) {
			if(e instanceof ExcelException){
				throw (ExcelException)e;
			}
			logger.error("", e);
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * 遍历工作簿的sheet，对每个sheet进行模板导出。
	 * @param context :
	 * @param inWb :
	 * @param outWb :
	 * @return void
	 * @author SHJ
	 * @since 2018/7/8 13:52
	 */
	public static void parseWorkbook(Object context, XSSFWorkbook inWb, SXSSFWorkbook outWb) throws ExcelException {
		try {
			int inSheetCount = inWb.getNumberOfSheets(); //模板中所有sheet数量

			for (int inSheetIndex = 0; inSheetIndex < inSheetCount; inSheetIndex++) {
				//创建sheet
				Sheet inSheet = inWb.getSheetAt(inSheetIndex);
				SXSSFSheet outSheet =  outWb.createSheet(inSheet.getSheetName());

				//设置输出sheet的列宽和模板一致
				int maxCellNum = CacheSheetUtils.getMaxCellNum(inSheet);
				int[] cellWidths = CacheSheetUtils.getCellWidths(inSheet, maxCellNum);
				for (int i = 0; i < maxCellNum; i++) {
					outSheet.setColumnWidth(i, cellWidths[i]);
				}

				//根据模板边读边写导出数据
				CacheExcelParser.parse(context, outWb, inSheet, outSheet, inSheet.getFirstRowNum(), inSheet.getLastRowNum());
			}
		} catch (Exception e) {
			if(e instanceof ExcelException){
				throw (ExcelException)e;
			}
			logger.error("", e);
			throw new ExcelException(e.getMessage());
		} finally {
			CacheExcelUtils.context.set(null);
		}
	}

	/**  get/set方法 */
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

}