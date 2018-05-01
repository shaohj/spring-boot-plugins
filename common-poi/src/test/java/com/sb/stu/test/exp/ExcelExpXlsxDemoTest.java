package com.sb.stu.test.exp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelExpXlsxDemoTest {

	private static Logger logger = LoggerFactory.getLogger(ExcelExpXlsTest.class);

	public static final String exportPath = "C:\\Users\\SHJ\\Desktop\\temp\\export\\";

	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("bh", "888888");
		params.put("jjqc", "基金全称内容");
		params.put("jjdm", "基金代码内容");
		params.put("fwfw", "服务范围内容");
		params.put("tgjgmc", "托管机构名称内容");

		params.put("cpjgsffj", "产品结构是否分级内容");
		params.put("dxjgmc", "代销机构名称内容");
		params.put("dxjgdm", "代销机构代码内容");
		params.put("sgzjjsr", "申购资金交收日内容");
		params.put("hsjgjsr", "赎回资金交收日内容");

		params.put("mjjszjgjzhmc", "募集结算资金归集账户名称内容");
		params.put("mjjszjhjzhhm", "募集结算资金归集账户代码内容");
		params.put("sqrsgfyzhmc", "收取行政管理服务费账户名称内容");
		params.put("sqrsgfyzhhm", "收取行政管理服务费账户号码内容");
		params.put("sqrsgfyzhkhh", "收取行政管理服务费账户开户行内容");

		params.put("sqrsgfyzhdezfh", "收取行政管理服务费账户大额支付号内容");
		params.put("jjglr", "基金管理人内容");
		params.put("jjxzglr", "基金行政管理人内容");

		ExcelUtils.addValue("params", params);
		ExcelUtils.addValue("year", "2017");
		ExcelUtils.addValue("month", "5");
		ExcelUtils.addValue("day", "23");

		String tempPath = "xlsx/";
		String tempFilePath = tempPath + "demo_template.xlsx";

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(exportPath + "demo_template_exp.xlsx");
			ExcelUtils.export(tempFilePath, fos);
		} catch (FileNotFoundException | ExcelException ex) {
			logger.error("{}", ex);
		} finally {
			IOUtils.closeQuietly(fos);
		}
	}

	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

}
