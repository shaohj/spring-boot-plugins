package com.sb.stu.commonfreemarker;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.sb.stu.commonfreemarker.util.FreeMarkerManager;
import com.sb.stu.commonfreemarker.util.FreeMarkerUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author ShaoHj
 *
 */
public class FreeMarterManagerTest {

	
private static final Logger logger = LoggerFactory.getLogger(FreeMarkerUtil.class);
	
	public static final String EXPORT_WORD_PATH = "C:\\Users\\SHJ\\Desktop\\temp\\wordExport\\";

	/**
	 * 使用模板导出
	 * @Title: reportCommon
	 */
	@Test
	public void reportCommon(){
		Map<String, Object> msgMap = new HashMap<String, Object>();
		
		try {
			msgMap.put("userName", "张三");
			msgMap.put("sex", "男");
			msgMap.put("address", "深圳福田区");
			logger.info("msg={}", FreeMarkerUtil.buildFreeMarkStream(msgMap, "testtmp.ftl"));
		} catch (Exception e) {
			logger.error("{}", e);
		}
	}
	
	/**
	 * 导出doc
	 * @Title: reportDoc
	 */
	@Test
	public void reportDoc(){
		Map<String, Object> msgMap = new HashMap<String, Object>();
		
		try {
			msgMap.put("startNo", "你好,张三");
			msgMap.put("numNo", "10086");
			msgMap.put("title", "freemarker word 模板导出doc");
			msgMap.put("endMsg", "导出结束啦");
			
			String toFilePath = EXPORT_WORD_PATH + "/中文名导出测试.doc";
			
			FreeMarkerUtil.buildFreeMarkFile(msgMap, "doc_temp.ftl", toFilePath);
			logger.info("导出doc成功，文件路径为{}", toFilePath);
		} catch (Exception e) {
			logger.error("{}", e);
		}
	}
	
	/**
	 * 导出docx
	 * @Title: reportDocx
	 */
	@Test
	public void reportDocx(){
		Map<String, Object> msgMap = new HashMap<String, Object>();
		
		//3.导出docx,file导出,String导出会长度溢出
		try {
			msgMap.put("startNo", "你好,张三");
			msgMap.put("numNo", "10086");
			msgMap.put("title", "freemarker word 模板导出docx");
			msgMap.put("endMsg", "导出结束啦");
			
			String toFilePath = EXPORT_WORD_PATH + "中文名导出测试.docx"; //实际导出文件的目录
			
			//生成模板xml
			String documentXmlPath = EXPORT_WORD_PATH + "中文docx模板替换文件.xml"; //存放生成的xml文件路径
			FreeMarkerUtil.buildFreeMarkFile(msgMap, "china_docx_temp.ftl", documentXmlPath);
			logger.info("导出xml成功，文件路径为{}", documentXmlPath);
			
			//导出docx
			String tempZip = FreeMarkerManager.WORD_TEMP_BASE_PATH + FreeMarkerManager.WORD_DOCX_TEMPLATE_PATH + "\\中文名导出.docx";
			FreeMarkerUtil.generateDocx(new File(documentXmlPath), tempZip, toFilePath);
			logger.info("导出docx成功，文件路径为{}", toFilePath);
		} catch (Exception e) {
			logger.error("{}", e);
		}
	}

}
