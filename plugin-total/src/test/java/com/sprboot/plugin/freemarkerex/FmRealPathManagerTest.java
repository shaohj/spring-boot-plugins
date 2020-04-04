package com.sprboot.plugin.freemarkerex;

import com.sprboot.plugin.freemarkerex.constants.FmTempFilePathEnum;
import com.sprboot.plugin.freemarkerex.util.FreeMarkerUtil;
import com.sprboot.plugin.freemarkerex.util.WordUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.sprboot.plugin.freemarkerex.constants.FreemarkConstants.*;

/**
 * 编  号：
 * 名  称：FmRealPathManagerTest
 * 描  述：真实路径导出测试
 * 完成日期：2020/3/29 20:14
 * @author：felix.shao
 */
@Slf4j
public class FmRealPathManagerTest {

	public static final String EXPORT_WORD_PATH = "E:\\temp\\wordExport\\";

	/**
	 * 根据ftl模板文件输出string
	 */
	@Test
	public void parseToStringTest(){
		Map<String, Object> msgMap = new HashMap<String, Object>(8);
		msgMap.put("userName", "张三");
		msgMap.put("sex", "男");
		msgMap.put("address", "深圳福田区");

		try {
			log.info("\n-->parseResult=\n{}", FreeMarkerUtil.parseTFToString(FmTempFilePathEnum.REAL_PATH, msgMap, "testtmp.ftl"));
		} catch (Exception e) {
			log.error("{}", e);
		}
	}

	/**
	 * 模板导出doc
	 */
	@Test
	public void parseDocTest(){
		Map<String, Object> msgMap = new HashMap<String, Object>(8);
		msgMap.put("startNo", "你好,张三");
		msgMap.put("numNo", "10086");
		msgMap.put("title", "freemarker word 模板导出doc");
		msgMap.put("endMsg", "导出结束啦");

		String fromPath = WORD_TEMP_BASE_REALPATH + WORD_DOC_TEMPLATE_PATH + "\\中文名导出.doc";
		String toPath = EXPORT_WORD_PATH + "中文名导出测试.doc";
		try {
			WordUtils.parseDoc(FmTempFilePathEnum.REAL_PATH, msgMap, fromPath, toPath);
		} catch (Exception e) {
			log.error("{}", e);
		}
	}
	
	/**
	 * 导出docx
	 */
	@Test
	public void reportDocx(){
		Map<String, Object> msgMap = new HashMap<String, Object>(8);
		msgMap.put("startNo", "你好,张三");
		msgMap.put("numNo", "10086");
		msgMap.put("title", "freemarker word 模板导出docx");
		msgMap.put("endMsg", "导出结束啦");


		String fromPath = WORD_TEMP_BASE_REALPATH + WORD_DOCX_TEMPLATE_PATH + "\\中文名导出.docx";
		String toPath = EXPORT_WORD_PATH + "中文名导出测试.docx";

		//导出docx,file导出,String导出时文件太大会溢出
		try {
			//导出docx
			WordUtils.parseDocx(FmTempFilePathEnum.REAL_PATH, msgMap, fromPath, toPath);
		} catch (Exception e) {
			log.error("{}", e);
		}
	}

	/**
	 * 导出docx
	 * @Title: reportDocx
	 */
	//@Test
	//public void reportDocx(){
	//	Map<String, Object> msgMap = new HashMap<String, Object>(8);
	//	msgMap.put("startNo", "你好,张三");
	//	msgMap.put("numNo", "10086");
	//	msgMap.put("title", "freemarker word 模板导出docx");
	//	msgMap.put("endMsg", "导出结束啦");
    //
	//	String toFilePath = EXPORT_WORD_PATH + "中文名导出测试.docx"; //实际导出文件的目录
    //
	//	//导出docx,file导出,String导出时文件太大会溢出
	//	try {
	//		//生成模板xml
	//		String documentXmlPath = EXPORT_WORD_PATH + "中文docx模板替换文件.xml"; //存放生成的xml文件路径
	//		FreeMarkerUtil.parseTFToFile(FmTempFilePathEnum.REAL_PATH, msgMap, "china_docx_temp.ftl", documentXmlPath);
	//		log.info("\n-->导出xml成功，文件路径为{}", documentXmlPath);
    //
	//		//导出docx
	//		String tempZip = WORD_TEMP_BASE_REALPATH + WORD_DOCX_TEMPLATE_PATH + "\\中文名导出.docx";
	//		WordUtils.parseDoc(FmTempFilePathEnum.REAL_PATH, new File(documentXmlPath), tempZip, toFilePath);
	//		log.info("\n-->导出docx成功，文件路径为{}", toFilePath);
	//	} catch (Exception e) {
	//		log.error("{}", e);
	//	}
	//}

}
