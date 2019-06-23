package net.javamail.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.junit.Test;

import net.javamail.ServiceResponse;
import net.javamail.event.SendDownCallEventDefault;
import net.javamail.model.param.AttachFileParam;
import net.javamail.model.param.SendMailParam;
import net.javamail.model.param.ZipParam;
import net.javamail.util.SendMailAsynUtils;
import net.javamail.util.MailParamUtils;


/**
 * SendMailUtil测试
 * 编  号：<br/>
 * 名  称：SendMailUtilTest<br/>
 * 描  述：<br/>
 * 完成日期：2016年11月14日 下午6:55:57<br/>
 * 编码作者：ShaoHanJie<br/>
 */
public class SendMailAsynUtilsTest {

	public static final String ACCOUNT = "shjLife@163.com";
	
	public static final String PASSWORD = "a1321916356";
	
	public static final String RECIPIENT = "728060301@qq.com";
	
	@SuppressWarnings("static-access")
	@Test
	public void sendMultipleEmailTest(){
		List<String> addressList = new ArrayList<String>();
		addressList.add(RECIPIENT);
		
		//发送人,收件人地址
		InternetAddress sender = null;
		InternetAddress[] toRecipients = null;
		try {
			sender = new InternetAddress(ACCOUNT);
			toRecipients = MailParamUtils.parseInternetAddress(addressList);
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		//邮件附件
		//List<AttachFileParam> attachFiles = new ArrayList<AttachFileParam>();
		//
		//AttachFileParam file1 = AttachFileParam.Builder
		//		.path("D:\\attach1.txt")
		//		.name("attach1.txt");
		//attachFiles.add(file1);
		//
		//AttachFileParam file2 = AttachFileParam.Builder
		//		.path("D:\\attach2.txt")
		//		.name("attach2.txt");
		//attachFiles.add(file2);
		
		//邮件正文
		StringBuffer contentBuf = new StringBuffer();
		contentBuf.append(
				"<span style='color:red;'>这是我自己用java mail发送的邮件哦...</span>");
		
		SendMailParam param = SendMailParam.builder()
				.protocol("smtp")
				.host("smtp.163.com")
				.port("25")
				.isAuth("true")
				.isEnabledDebugMod("true")
				.sender(sender) //发件人及发件人账号,请用自己的账号,密码
				.account(ACCOUNT)
				.password(PASSWORD) //发件人密码,请用自己的账号,密码
				.sentDate(new Date())
				.subject("使用JavaMail发送混合组合类型的邮件测试主题(异步)")
				//.attachFiles(attachFiles)
				.content(contentBuf.toString())
				.toRecipients(toRecipients).build();
		
		param.initDefaultProps();
		
		//ZipParam zipParam = ZipParam.Builder
		//		.name("附件列表.zip")
		//		.path("D:/Program Files (x86)/temp/zipTemp/附件列表.zip");
		//
		//param.zipFlag(true);
		//param.zipParam(zipParam);
		
		Map<String, String> exParams = new HashMap<String, String>();
		exParams.put("user", "123");
		
		param.exParams(exParams);
		
		Executor taskExecutor = Executors.newFixedThreadPool(5);
		
		ServiceResponse<String> serviceResponse = SendMailAsynUtils.sendEmail(taskExecutor,
				param, new SendDownCallEventDefault());
		
		System.out.println("errorNO = "+ serviceResponse.getErrorNO());
		System.out.println("errorMsg = "+ serviceResponse.getErrorMsg());
		
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
