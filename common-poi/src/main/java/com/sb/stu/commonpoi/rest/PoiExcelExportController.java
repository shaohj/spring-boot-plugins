package com.sb.stu.commonpoi.rest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.sb.stu.commonpoi.entity.Model;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

@Controller
public class PoiExcelExportController {

	private static Logger logger = LoggerFactory.getLogger(PoiExcelExportController.class);

	public static final String exportPath = "C:/Users/dell/Desktop/temp/export";

	// 下载测试：http://127.0.0.1:8080/test/rest/excel/exportByDown?fileName=poi_template.xlsx
	@GetMapping(path = "/excel/exportByDown", produces = "application/json; charset=utf-8")
	public void exportExcelByDown(@RequestParam String fileName, HttpServletResponse response) {
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

		try {
			response.setHeader("Content-Disposition", "attachment;Filename=" + URLEncoder.encode(fileName, "UTF-8"));
			response.setHeader("Connection", "close");
			response.setHeader("Content-Type", "application/vnd.ms-excel");

			ExcelUtils.export(fileName, response.getOutputStream()); // demo.xlsx
		} catch (Exception ex) {
			logger.error("{}", ex);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(path = "/excel/export", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object exportExcel() {
		System.out.println("***********************************exportExcel**********");

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("error_no", "0");
		result.put("error_msg", "ok!");

		Model model = new Model("aaa1", "bbb", 123.234);

		List details = new ArrayList();
		details.add(new Model("user1", "kong", 1234.342));
		details.add(new Model("user2", "hello", 1224.342));
		details.add(new Model("user3", "world", 144.342));

		ExcelUtils.addValue("printDate", getCurrentDate("yyyy-MM-dd"));
		ExcelUtils.addValue("model", model);
		ExcelUtils.addValue("list", details);
		String config = "demo.xlsx";

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(exportPath + "/" + "demo2.xlsx");
			ExcelUtils.export(config, fos);

		} catch (FileNotFoundException | ExcelException ex) {
			logger.error("{}", ex);
		} finally {
			IOUtils.closeQuietly(fos);
		}

		return result;
	}

	private static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
}
