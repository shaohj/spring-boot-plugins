package net.javamail.util;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import net.javamail.ServiceResponse;
import net.javamail.util.EmlUtils;

public class EmlUtilsTest {

	@Test
	public void parseAttachFilesTest(){
		String emlPath = "C:\\Users\\SHJ\\Desktop\\temp\\emldown\\邮箱大师邮件群功能，解放你的收件人列表.eml";
		String storePath = "C:\\Users\\SHJ\\Desktop\\temp\\emailAttachfiles";
		ServiceResponse<List<String>> serviceResponse = EmlUtils.parseAttachFiles(emlPath, storePath);

		System.out.println("errorNO = " + serviceResponse.getErrorNO());
		System.out.println("errorMsg = " + serviceResponse.getErrorMsg());
		System.out.println("result = " + JSON.toJSONString(serviceResponse.getResult()));
	}
	
}
