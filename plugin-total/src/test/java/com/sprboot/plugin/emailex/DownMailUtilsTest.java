package com.sprboot.plugin.emailex;

import com.alibaba.fastjson.JSON;
import com.sprboot.plugin.emailex.bean.ServiceResponse;
import com.sprboot.plugin.emailex.bean.param.DownMailParam;
import com.sprboot.plugin.emailex.util.DownMailUtils;
import org.junit.Test;

import java.util.List;

/**
 * 编  号：
 * 名  称：DownMailUtilsTest
 * 描  述：
 * 完成日期：2020/3/29 19:28
 * @author：felix.shao
 */
public class DownMailUtilsTest {


	public static final String ACCOUNT = "shjLife@163.com";
	
	public static final String PASSWORD = "a1321916356";
	
	@Test
	public void downMultipleEmailTest(){
		DownMailParam param = DownMailParam.builder()
				.protocol("smtp")
				.type("pop3")
				.port(110)
				.recHost("pop.163.com")
				.isAuth("true")
				.isEnabledDebugMod("false")
				.account(ACCOUNT)
				.password(PASSWORD) //收件人密码,请用自己的账号,密码
				.subject("邮箱大师邮件群功能，解放你的收件人列表") //主题匹配条件
				.path("C:\\Users\\SHJ\\Desktop\\temp\\emldown").build();
		
		param.initDefaultProps();

		ServiceResponse<List<String>> serviceResponse = DownMailUtils.downMultipleEmail(param);
		
		System.out.println("errorNO = " + serviceResponse.getErrorNO());
		System.out.println("errorMsg = " + serviceResponse.getErrorMsg());
		System.out.println("result = " + JSON.toJSONString(serviceResponse.getResult()));
	}
	
}
