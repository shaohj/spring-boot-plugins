package com.sprboot.plugin.emailex;

import com.alibaba.fastjson.JSON;
import com.sprboot.plugin.emailex.bean.ServiceResponse;
import com.sprboot.plugin.emailex.util.EmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * 编  号：
 * 名  称：EmlUtilsTest
 * 描  述：
 * 完成日期：2019/6/23 12:32
 * @author：felix.shao
 */
@Slf4j
public class EmlUtilsTest {

	@Test
	public void parseAttachFilesTest(){
		String emlPath = "C:\\Users\\SHJ\\Desktop\\temp\\emldown\\邮箱大师邮件群功能，解放你的收件人列表.eml";
		String storePath = "C:\\Users\\SHJ\\Desktop\\temp\\emailAttachfiles";
		ServiceResponse<List<String>> serviceResponse = EmlUtils.parseAttachFiles(emlPath, storePath);

		log.info("\n-->errorNO = " + serviceResponse.getErrorNO());
		log.info("\n-->errorMsg = " + serviceResponse.getErrorMsg());
		log.info("\n-->result = " + JSON.toJSONString(serviceResponse.getResult()));
	}
	
}
