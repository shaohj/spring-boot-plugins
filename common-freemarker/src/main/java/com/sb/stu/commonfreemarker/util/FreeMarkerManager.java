/*
 * 文件名称: FreeMarkerManager.java
 * 版权信息: Copyright 2015-2016 MOPO Inc. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史: 
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: lihai
 * 修改日期: 2015年10月23日
 * 修改内容: 
 */
package com.sb.stu.commonfreemarker.util;

import java.io.File;
import java.io.IOException;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * 
 * @author ShaoHj
 *
 */
public class FreeMarkerManager {
	
	public static final Version VERSION = new Version("2.3.26");

	/** word存放的基础目录 */
	public static final String WORD_TEMP_BASE_PATH = "C:\\Users\\SHJ\\Desktop\\temp\\word_template\\";

	/** word的ftl模板文件存放目录 */
	public static final String FTL_PATH = "ftl";

	/** word的docx模板文件存放目录 */
	public static final String WORD_DOCX_TEMPLATE_PATH = "docx";

	private static Configuration cfg = new Configuration(VERSION);

	static {
		//定义模板的位置，从类路径相对FreeMarkerManager所在的模板加载路径
		TemplateLoader tempLoader = null; 
		
		//tempLoader = new ClassTemplateLoader(FreeMarkerManager.class, WORD_FTL_TEMPLATE_PATH); //类路径下的文件每次修改,服务器都需要重启,不推荐该方法

//		try { //web-inf:路径读取备份
//			
//			//file:/D:/workSpaces/ws1/springBootFreemarker/target/classes/
//	        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
//	        path += "templates/ftl";
//	        String ftlPath = "";
//	        if("windows".equalsIgnoreCase(OSinfo.getOSname().toString())){
//	        	//window路径处理
//	        	ftlPath = path.replace("file:", "").replace("classes\\", "").replace('/', '\\').substring(1).replace("%20", " ");
//	        }else{
//	        	//linux路径处理 
//	        	ftlPath = path.replace("file:", "").replace("classes/", "");
//	        }
//	        
//			File file = new File(ftlPath);
//			tempLoader = new FileTemplateLoader(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		try { //指定路径加载,替换模板文件后不会重启服务器,推荐使用
	        String path = WORD_TEMP_BASE_PATH + FTL_PATH;
			File file = new File(path);
			tempLoader = new FileTemplateLoader(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		cfg.setTemplateLoader(tempLoader); 
		cfg.setNumberFormat("0");
		//设置对象的包装器
		cfg.setObjectWrapper(new DefaultObjectWrapper(VERSION));
		//设置异常处理器	${a.b.c.d}即使没有属性也不会出错
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		cfg.setDefaultEncoding("UTF-8");
	}

	public static Configuration getConfiguration() {
		return cfg;
	}
}
