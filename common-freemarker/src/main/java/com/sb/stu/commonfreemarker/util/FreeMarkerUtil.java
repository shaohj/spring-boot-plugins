package com.sb.stu.commonfreemarker.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.util.IOUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 编  号：
 * 名  称：FreeMarkerUtil
 * 描  述：
 * 完成日期：2018/8/4 15:29
 * @author：felix.shao
 */
public class FreeMarkerUtil {

	private static final Logger logger = LoggerFactory.getLogger(FreeMarkerUtil.class);
	
	/**
	 * 根据freemark模板导出生成文件
	 * @param msgMap
	 * @param templatePath 模板文件路径,模板名称不支持中文路径
	 * @param toFilePath 导出后生成文件路径,支持含中文名路径
	 * @return
	 */
	public static void buildFreeMarkFile(Map<String, Object> msgMap, String templatePath, String toFilePath) {
		Writer out = null;
		FileOutputStream fos = null;
		
		/** 创建导出父文件夹 */
		FileUtils.createParentFolder(toFilePath);
		
		try {
			Configuration cfg = FreeMarkerManager.getConfiguration();

			Template template = cfg.getTemplate(templatePath);
			
			File outFile = new File(toFilePath);  
	        
	        fos = new FileOutputStream(outFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");  
            out = new BufferedWriter(oWriter); 
			
            template.process(msgMap, out);  
		} catch (Exception e) {
			logger.error("Failed to buildFreeMarkFile.{}", e);
		} finally {
			IOUtils.close(out);
			IOUtils.close(fos);
		}
	}
	
    /** 
     * 替换模板文件的word/document.xml,生成新的docx,String导出会长度溢出
     * @param documentXmlFile 动态生成数据的docunment内容文件
     * @param docxFilePath docx的模板,支持中文名
     * @throws ZipException
     * @throws IOException 
     */  
    public static void generateDocx(File documentXmlFile, String docxFilePath, String toFilePath) throws ZipException, IOException {  
    	//URL url = FreeMarkerUtil.class.getClassLoader().getResource(docxFilePath);
    	URL url = new File(docxFilePath).toURI().toURL();

    	ZipFile zipFile = null;
    	ZipOutputStream zipout = null;
    	
    	/** 创建导出父文件夹 */
    	FileUtils.createParentFolder(toFilePath);
    	
    	try {  
    		zipFile = new ZipFile(URLDecoder.decode(url.getPath(), "UTF-8"), "UTF-8");           
    		Enumeration<? extends ZipEntry> zipEntrys = zipFile.getEntries();  
    		zipout = new ZipOutputStream(new FileOutputStream(toFilePath));
    		zipout.setEncoding("UTF-8");
    		int len=-1;  
    		byte[] buffer=new byte[1024];  
    		while(zipEntrys.hasMoreElements()) {  
    			ZipEntry next = zipEntrys.nextElement();  
    			InputStream is = zipFile.getInputStream(next);  
    			//把输入流的文件传到输出流中 如果是word/document.xml由我们输入  
    			zipout.putNextEntry(new ZipEntry(next.toString()));  
    			if("word/document.xml".equals(next.toString())){  
    				InputStream in = new FileInputStream(documentXmlFile);
    				while((len = in.read(buffer))!=-1){  
    					zipout.write(buffer,0,len);  
    				}
    				IOUtils.close(in);
    			}else {  
    				while((len = is.read(buffer))!=-1){  
    					zipout.write(buffer,0,len);  
    				}  
    				IOUtils.close(is);
    			}         
    		}             
    	} catch (FileNotFoundException e) {  
    		logger.error("Failed to generateDocx.{}", e);
    	} finally{
    		IOUtils.close(zipout);
    		IOUtils.close(zipFile);
    	}
    }  
    
    /**
	 * 根据freemark模板输出html数据流  模板引擎解释模板
	 * @param msgMap
	 * @param templatePath
	 * @return
	 */
	public static String buildFreeMarkStream(Map<String, Object> msgMap, String templatePath) {
		StringWriter stringWriter = new StringWriter();
		try {
			Configuration cfg = FreeMarkerManager.getConfiguration();
			Template template = cfg.getTemplate(templatePath);
			template.process(msgMap, new PrintWriter(stringWriter));
			StringBuffer buffer = stringWriter.getBuffer();
			return new String(buffer.toString().getBytes("UTF-8"),"UTF-8");
		} catch (Exception e) {
			logger.error("Failed to build freeMarker stream.{}", e);
		}
		return null;
	}

	/**
	 * 根据freemark模板生成静态页面
	 * @param msgMap
	 * @param path
	 * @param templatePath
	 * @param templateName
	 */
	public static void buildFreeMarkHtml(Map<String, Object> msgMap, File path, String templatePath, String templateName) {
		FileOutputStream outStream = null;
		BufferedWriter sw = null;
		
		try {
			if (!path.exists()) {
				path.mkdirs();
			}
			Configuration cfg = FreeMarkerManager.getConfiguration();
			Template template = cfg.getTemplate(templatePath);
			outStream = new FileOutputStream(new File(path, templateName + ".html"));
			OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
			sw = new BufferedWriter(writer);
			//模板引擎解释模板
			template.process(msgMap, sw);
			sw.flush();
		} catch (Exception e) {
			logger.error("Failed to build freeMarker static html.{}", e);
		} finally {
			IOUtils.close(sw);
			IOUtils.close(outStream);
		}
	}
	
}
