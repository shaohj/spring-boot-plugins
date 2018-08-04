/*
 * Copyright 2003-2005 ExcelUtils http://excelutils.sourceforge.net
 * Created on 2005-7-5
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

package com.poi.template.excel.parse.nocache;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletContext;

import com.poi.template.excel.exception.ExcelException;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExcelUtils {

	static ThreadLocal context = new ThreadLocal();

	/**
	 * 初始化配置
	 * 
	 * @param wb
	 */
	public static void initExcelProperties(Workbook wb) {
		wb.setForceFormulaRecalculation(true); // 当整个workbook打开时,刷新工作表
	}

	/**
	 * parse the excel template and output excel to outputStream.
	 * 
	 * @param ctx
	 *            ServletContext
	 * @param config
	 *            Excel Template Name
	 * @param context
	 *            All Data
	 * @param out
	 *            OutputStream
	 * @throws ExcelException
	 */
	public static void export(ServletContext ctx, String config, Object context, OutputStream out)
			throws ExcelException {
		try {
			Workbook wb = WorkbookUtils.openWorkbook(ctx, config);
			initExcelProperties(wb);

			parseWorkbook(context, wb);
			wb.write(out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * parse the excel template in a sheet and output excel to outputStream.
	 * 
	 * @param ctx
	 * @param config
	 * @param sheetIndex
	 * @param context
	 * @param out
	 * @throws ExcelException
	 */
	public static void export(ServletContext ctx, String config, int sheetIndex, Object context, OutputStream out)
			throws ExcelException {
		try {
			Workbook wb = WorkbookUtils.openWorkbook(ctx, config);
			initExcelProperties(wb);

			parseWorkbook(context, wb, sheetIndex);
			wb.write(out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * parse the excel template and output excel to outputStream in default
	 * context.
	 * 
	 * @param ctx
	 * @param config
	 * @param out
	 * @throws ExcelException
	 */
	public static void export(ServletContext ctx, String config, OutputStream out) throws ExcelException {
		try {
			export(ctx, config, getContext(), out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * parse the excel template in a sheet and output excel to outputStream in
	 * default context.
	 * 
	 * @param ctx
	 * @param config
	 * @param sheetIndex
	 * @param out
	 * @throws ExcelException
	 */
	public static void export(ServletContext ctx, String config, int sheetIndex, OutputStream out)
			throws ExcelException {
		try {
			export(ctx, config, sheetIndex, getContext(), out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * parse excel and export
	 * 
	 * @param fileName
	 * @param context
	 * @param out
	 * @throws ExcelException
	 */
	public static void export(String fileName, Object context, OutputStream out) throws ExcelException {
		try {
			Workbook wb = WorkbookUtils.openWorkbook(fileName);
			initExcelProperties(wb);

			parseWorkbook(context, wb);
			wb.write(out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * parse exel and export
	 * 
	 * @param fileName
	 * @param sheetIndex
	 * @param out
	 * @throws ExcelException
	 */
	public static void export(String fileName, int sheetIndex, Object context, OutputStream out) throws ExcelException {
		try {
			Workbook wb = WorkbookUtils.openWorkbook(fileName);
			initExcelProperties(wb);

			parseWorkbook(context, wb, sheetIndex);
			wb.write(out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * parse excel and export excel
	 * 
	 * @param fileName
	 * @param out
	 * @throws ExcelException
	 */
	public static void export(String fileName, OutputStream out) throws ExcelException {
		try {
			export(fileName, getContext(), out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * parse excel and export excel
	 * 
	 * @param fileName
	 * @param sheetIndex
	 * @param out
	 * @throws ExcelException
	 */
	public static void export(String fileName, int sheetIndex, OutputStream out) throws ExcelException {
		try {
			export(fileName, sheetIndex, getContext(), out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * @param inputStream
	 * @param context
	 * @param out
	 * @throws ExcelException
	 */
	public static void export(String url, InputStream inputStream, Object context, OutputStream out)
			throws ExcelException {
		try {
			Workbook wb = WorkbookUtils.openWorkbook(url, inputStream);
			initExcelProperties(wb);

			parseWorkbook(context, wb);
			wb.write(out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * parse workbook
	 * 
	 * @param context
	 * @param wb
	 * @throws ExcelException
	 */
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

	/**
	 * parse Workbook
	 * 
	 * @param context
	 * @param wb
	 * @param sheetIndex
	 * @throws ExcelException
	 */
	public static void parseWorkbook(Object context, Workbook wb, int sheetIndex) throws ExcelException {
		try {
			Sheet sheet = wb.getSheetAt(sheetIndex);
			if (null != sheet) {
				parseSheet(context, sheet);
			}

			int i = 0;
			while (i++ < sheetIndex) {
				wb.removeSheetAt(0);
			}

			i = 1;
			while (i < wb.getNumberOfSheets()) {
				wb.removeSheetAt(i);
			}
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * parse Excel Template File
	 * 
	 * @param context
	 *            datasource
	 * @param sheet
	 *            Workbook sheet
	 */
	public static void parseSheet(Object context, Sheet sheet) throws ExcelException {
		try {
			ExcelParser.parse(context, sheet, sheet.getFirstRowNum(), sheet.getLastRowNum());
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		} finally {
			ExcelUtils.context.set(null);
		}
	}

	public static void addService(Object context, String key, Object service) {
		addValue(context, key, service);
	}

	public static void addService(String key, Object service) {
		addValue(key, service);
	}

	/**
	 * add a object to context
	 * 
	 * @param context
	 *            must be a DynaBean or Map type
	 * @param value
	 */
	public static void addValue(Object context, String key, Object value) {
		if (context instanceof DynaBean) {
			((DynaBean) context).set(key, value);
		} else if (context instanceof Map) {
			((Map) context).put(key, value);
		}
	}

	/**
	 * add a object to default context
	 * 
	 * @param key
	 * @param value
	 */
	public static void addValue(String key, Object value) {
		getContext().set(key, value);
	}

	/**
	 * register extended tag package, default is net.sf.excelutils.tags
	 * 
	 * @param packageName
	 */
	public synchronized static void registerTagPackage(String packageName) {
		ExcelParser.tagPackageMap.put(packageName, packageName);
	}

	/**
	 * get a global context, it's thread safe
	 * 
	 * @return DynaBean
	 */
	public static DynaBean getContext() {
		DynaBean ctx = (DynaBean) context.get();
		// System.out.println("threadName = " +
		// Thread.currentThread().getName());
		if (null == ctx) {
			ctx = new LazyDynaBean();
			setContext(ctx);
		}
		return ctx;
	}

	/**
	 * set global context
	 * 
	 * @param ctx
	 *            DynaBean
	 */
	public static void setContext(DynaBean ctx) {
		context.set(ctx);
	}

	/**
	 * can value be show
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean isCanShowType(Object value) {
		if (null == value)
			return false;
		String valueType = value.getClass().getName();
		return "java.lang.String".equals(valueType) || "java.lang.Double".equals(valueType)
				|| "java.lang.Integer".equals(valueType) || "java.lang.Boolean".equals(valueType)
				|| "java.sql.Timestamp".equals(valueType) || "java.util.Date".equals(valueType)
				|| "java.lang.Byte".equals(valueType) || "java.math.BigDecimal".equals(valueType)
				|| "java.math.BigInteger".equals(valueType) || "java.lang.Float".equals(valueType)
				|| value.getClass().isPrimitive();
	}
}