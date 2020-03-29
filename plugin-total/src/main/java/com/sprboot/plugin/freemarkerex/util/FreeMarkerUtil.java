package com.sprboot.plugin.freemarkerex.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.sprboot.plugin.freemarkerex.constants.FmTempFilePathEnum;
import lombok.extern.slf4j.Slf4j;

import com.alibaba.fastjson.util.IOUtils;

import freemarker.template.Template;

/**
 * 编  号：
 * 名  称：FreeMarkerUtil
 * 描  述：注意，模板文件路径,模板名称不支持中文路径，即ftl模板文件写为英文即可
 * 完成日期：2020/3/29 20:11
 * @author：felix.shao
 */
@Slf4j
public class FreeMarkerUtil {

	/**
	 * 根据模板文件输出string<br />
	 * 不适用大模板文件场景，否则会数据溢出<br />
	 * @param fmEnum 模板文件路径类型
	 * @param msgMap javabean
	 * @param templatePath 模板文件路径,模板名称不支持中文路径
	 * @return
	 */
	public static String parseTFToString(FmTempFilePathEnum fmEnum, Map<String, Object> msgMap, String templatePath) {
		StringWriter stringWriter = new StringWriter();
		try {
			Template template = FreeMarkerManager.singleConfiguration(fmEnum).getTemplate(templatePath);
			template.process(msgMap, new PrintWriter(stringWriter));
			StringBuffer buffer = stringWriter.getBuffer();
			return new String(buffer.toString().getBytes("UTF-8"),"UTF-8");
		} catch (Exception e) {
			log.error("Error by parseTFToString.{}", e);
		}
		return null;
	}

	/**
	 * 根据模板文件输出新html文件<br />
	 * @param fmEnum 模板文件路径类型
	 * @param msgMap javabean
	 * @param templatePath 模板文件路径,模板名称不支持中文路径
	 * @param path html文件的父目录
	 * @param templateName 模板文件名，无html后缀
	 */
	public static void parseTFToHtml(FmTempFilePathEnum fmEnum, Map<String, Object> msgMap, String templatePath, File path, String templateName) {
		if (!path.exists()) {
			path.mkdirs();
		}

		BufferedWriter sw = null;

		try {
			Template template = FreeMarkerManager.singleConfiguration(fmEnum).getTemplate(templatePath);
			sw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path, templateName + ".html")), "UTF-8"));
			//模板引擎解释模板
			template.process(msgMap, sw);
			sw.flush();
		} catch (Exception e) {
			log.error("Error by parseTFToHtml.{}", e);
		} finally {
			IOUtils.close(sw);
		}
	}

	/**
	 * 根据模板文件输出新文件<br />
	 * @param fmEnum 模板文件路径类型
	 * @param msgMap javabean
	 * @param templatePath 模板文件路径,模板名称不支持中文路径
	 * @param toFilePath 导出后生成文件路径,支持含中文名路径
	 * @return
	 */
	public static void parseTFToFile(FmTempFilePathEnum fmEnum, Map<String, Object> msgMap, String templatePath, String toFilePath) {
		/** 创建导出父文件夹 */
		FileUtils.createParentFolder(toFilePath);

		Writer out = null;
		try {
			Template template = FreeMarkerManager.singleConfiguration(fmEnum).getTemplate(templatePath);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(toFilePath)), "UTF-8"));
            template.process(msgMap, out);
			out.flush();
		} catch (Exception e) {
			log.error("Error by parseTFToFile.{}", e);
		} finally {
			IOUtils.close(out);
		}
	}
	
}
