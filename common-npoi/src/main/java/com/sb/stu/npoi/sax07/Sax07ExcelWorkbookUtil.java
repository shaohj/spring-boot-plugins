package com.sb.stu.npoi.sax07;

import cn.hutool.core.io.IoUtil;
import com.sb.stu.npoi.common.exception.ExcelException;
import com.sb.stu.npoi.common.util.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;

public class Sax07ExcelWorkbookUtil {

	public Sax07ExcelWorkbookUtil() {}

	public static XSSFWorkbook openWorkbookByProPath(String fileName) {
		InputStream in = null;
		XSSFWorkbook wb = null;
		try {
			in = Sax07ExcelWorkbookUtil.class.getClassLoader().getResourceAsStream(fileName);
			String fileSuffix = StringUtils.getStringSuffix(fileName);
			wb = openWorkbook(in, fileSuffix);
		} catch (Exception e) {
			throw new ExcelException("File" + fileName + "not found" + e.getMessage());
		} finally {
			IoUtil.close(in);
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

}
