package com.sb.stu.parse.cache.exp;

import com.poi.template.excel.exception.ExcelException;
import com.poi.template.excel.parse.cache.CacheExcelParser;
import com.poi.template.excel.parse.cache.poiutils.CacheExcelUtils;
import com.sb.stu.commonpoi.entity.Model;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class ExprTest {

	private static Logger logger = LoggerFactory.getLogger(ExprTest.class);

	public static final String exportPath = "E:\\temp\\export\\";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<>();

		params.put("printDate", LocalDateTime.now().toString().replace("T", " "));

		Map<String, Object> model = new HashMap<>();
		model.put("account", "zhangsan");
		model.put("name", "张三");
		params.put("model", model);

		List<String> listStr = Arrays.asList("aa", "bb", "cc");
		params.put("listStr", listStr);

		System.out.println(CacheExcelParser.parseStr(params, "${printDate}"));
		System.out.println(CacheExcelParser.parseStr(params, "${model.account}"));
		System.out.println(CacheExcelParser.parseStr(params, "${model.name}"));
		System.out.println(CacheExcelParser.parseStr(params, "${listStr[0]}"));
		System.out.println(CacheExcelParser.parseStr(params, "${listStr[${index}]}"));
	}


}
