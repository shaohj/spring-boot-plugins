package com.sb.stu.parse.cache.exp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sb.stu.commonpoi.entity.Model;
import com.poi.template.excel.exception.ExcelException;
import com.poi.template.excel.parse.cache.poiutils.CacheExcelUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelExpEachTest {

	private static Logger logger = LoggerFactory.getLogger(ExcelExpEachTest.class);

	public static final String exportPath = "E:\\temp\\export\\";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.io.tmpdir"));

		Model model = new Model("aaa1", "bbb", 123.234);

		model.setYear("1992");

		List details = new ArrayList();

		details.add(new Model("user1", "kong", 1234.342));
		details.add(new Model("user2", "hello", 1224.342));
		details.add(new Model("user3", "world", 144.342));

		CacheExcelUtils.addValue("printDate", getCurrentDate("yyyy-MM-dd"));
		CacheExcelUtils.addValue("model", model);

		//万条数据导出测试
		for(int i = 0; i< 1; i++){
			details.add(new Model("user3", "world", 144.342));
		}
		CacheExcelUtils.addValue("list", details);

		String tempPath = "xlsx/";
		String tempFilePath = tempPath + "demo_each.xlsx";

		FileOutputStream fos = null;
		try {
			long t1 = System.currentTimeMillis();
			fos = new FileOutputStream(exportPath + "demo_each_exp.xlsx");
			CacheExcelUtils.export(tempFilePath, fos);
			logger.info("导出{}条数据,耗费时间为{}毫秒", details.size(), System.currentTimeMillis() - t1);
		} catch (FileNotFoundException | ExcelException ex) {
			logger.error("{}", ex);
		} finally {
			IOUtils.closeQuietly(fos);
		}
	}

	private static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

}
