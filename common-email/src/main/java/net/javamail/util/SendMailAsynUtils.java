package net.javamail.util;

import lombok.extern.slf4j.Slf4j;
import net.javamail.ServiceResponse;
import net.javamail.event.ISendDownCallEvent;
import net.javamail.model.param.AttachFileParam;
import net.javamail.model.param.EmbedImageParam;
import net.javamail.model.param.SendMailParam;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.Executor;

/**
 * 编  号：
 * 名  称：SendMailAsynUtils
 * 描  述：异步发送邮件工具类
 * 完成日期：2018/8/4 15:14
 * @author：felix.shao
 */
@Slf4j
public class SendMailAsynUtils {
	
	public static ServiceResponse<String> sendEmail(Executor taskExecutor, SendMailParam param, ISendDownCallEvent callEvent) {
		ServiceResponse<String> validateResponse = MailParamUtils.validateSendMailParam(param);
		
		if(ServiceResponse.SUCCESS != validateResponse.getErrorNO()){
			return validateResponse;
		}
		
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				callEvent.handle(sendMultipleEmail(param), param.getExParams());
			}
		});
		
		return new ServiceResponse<String>(ServiceResponse.SUCCESS);
	}
	
	/**
	 * 发送带内嵌图片、附件、多收件人(显示邮箱姓名)、邮件优先级、阅读回执的完整的HTML邮件
	 * @Title: sendMultipleEmail 
	 * @param param
	 */
	private static ServiceResponse<String> sendMultipleEmail(SendMailParam param){
		int errorNO = ServiceResponse.SUCCESS;
		String errorMsg = "";
		
        Session session = Session.getInstance(param.getProps());  // 创建Session实例对象
         
        MimeMessage message = new MimeMessage(session);   // 创建MimeMessage实例对象
        Transport transport = null;
        try {
			message.setFrom(param.getSender());  // 设置发件人 
			message.setSentDate((param.getSentDate()==null) ? new Date(): param.getSentDate()); // 设置发送时间 
			message.setSubject(param.getSubject());  // 设置邮件主题 
			message.setContent(param.getContent(), "text/html;charset=utf-8"); // 设置邮件正文 
			message.setRecipients(RecipientType.TO, param.getToRecipients());  // 设置收件人 
			if(null != param.getCcRecipients() && param.getCcRecipients().length > 0){
				message.setRecipients(RecipientType.CC, param.getCcRecipients());  // 设置抄送收件人 
			}
			if(null != param.getBccRecipients() && param.getBccRecipients().length > 0){
				message.setRecipients(RecipientType.BCC, param.getBccRecipients());  // 设置密送收件人 
			}
			
			// 创建一个MIME子类型为"mixed"的MimeMultipart对象，表示这是一封混合组合类型的邮件 
	        MimeMultipart mailContent = new MimeMultipart("mixed");  
	        message.setContent(mailContent); 
	         
	        // 内容 
	        MimeBodyPart mailBody = new MimeBodyPart(); 
	        mailContent.addBodyPart(mailBody); 
	        
	        // 添加邮件附件
	        if(null != param.getAttachFiles() && param.getAttachFiles().size() > 0){
	        	if(!param.isZipFlag()){
	        		for(AttachFileParam attachFileParam: param.getAttachFiles()){
		        		addAttachFileMimeBodyPart(mailContent, attachFileParam.getName(), attachFileParam.getPath());
		        	}
	        	} else {
	        		ZipUtils.zip(param.getZipParam(), param.getAttachFiles());
	        		addAttachFileMimeBodyPart(mailContent, param.getZipParam().getName(), param.getZipParam().getPath());
	        	}
	        }
	         
	        // 邮件正文(内嵌图片+html文本) 
	        MimeMultipart body = new MimeMultipart("related");  //邮件正文也是一个组合体,需要指明组合关系 
	        mailBody.setContent(body); 
	         
	        // 添加邮件正文图片
	        if(null != param.getEmbedImages() && param.getEmbedImages().size() > 0){
	        	for(EmbedImageParam imgAttachParam: param.getEmbedImages()){
	        		addEmbedImageMimeBodyPart(body, imgAttachParam);
	        	}
	        }
	        
	        MimeBodyPart htmlPart = new MimeBodyPart(); 
	        body.addBodyPart(htmlPart);
	        // html邮件内容 
	        MimeMultipart htmlMultipart = new MimeMultipart("alternative");  
	        htmlPart.setContent(htmlMultipart); 
	        MimeBodyPart htmlContent = new MimeBodyPart(); 
	        htmlContent.setContent(param.getContent(), "text/html;charset=utf-8"); 
	        htmlMultipart.addBodyPart(htmlContent); 
			
			message.saveChanges(); // 保存并生成最终的邮件内容 
			 
			// 获得Transport实例对象 
			transport = session.getTransport(); 
			// 打开连接 
			transport.connect(param.getAccount(), param.getPassword()); 
			// 将message对象传递给transport对象，将邮件发送出去 
			transport.sendMessage(message, message.getAllRecipients()); 
		} catch (Exception e) {
			log.error("", e);
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsg = e.getMessage();
		} finally{
			try {if(null != transport){transport.close();}} catch (MessagingException e) {}
		}
        
        return new ServiceResponse<String>(errorNO, errorMsg);
	};
	
	/**
	 * 将内嵌图片添加至MimeMultipart中
	 * @Title: addEmbedImageMimeBodyPart 
	 * @param mailContent
	 * @param param
	 * @return
	 */
	private static ServiceResponse<MimeBodyPart> addEmbedImageMimeBodyPart(MimeMultipart mailContent, EmbedImageParam param){
		int errorNO = ServiceResponse.SUCCESS;
		String errorMsg = "";
		
		// 创建一个表示图片资源的MimeBodyPart对象，将将它加入到前面创建的MimeMultipart对象中 
        MimeBodyPart imagePart = new MimeBodyPart(); 
        try {
			mailContent.addBodyPart(imagePart);
			
			 // 设置内嵌图片邮件体 
	        DataSource ds = new FileDataSource(new File(param.getPath())); 
	        DataHandler dh = new DataHandler(ds); 
	        imagePart.setDataHandler(dh); 
	        imagePart.setContentID(param.getContentId());  // 设置内容编号,用于其它邮件体引用 
		} catch (MessagingException e) {
			log.error("{}", e);

			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsg = "添加嵌入图片失败."+param.getPath()+"."+e.getMessage();
		}
        return new ServiceResponse<MimeBodyPart>(errorNO, errorMsg, imagePart);
	}
	
	/**
	 * 将内嵌图片添加至MimeMultipart中
	 * @Title: addAttachFileMimeBodyPart 
	 * @param mailContent
	 * @param name
	 * @param path
	 * @return
	 */
	private static ServiceResponse<MimeBodyPart> addAttachFileMimeBodyPart(MimeMultipart mailContent, 
			String name, String path){
		int errorNO = ServiceResponse.SUCCESS;
		String errorMsg = "";
		
		// 创建一个表示图片资源的MimeBodyPart对象，将将它加入到前面创建的MimeMultipart对象中 
        MimeBodyPart attach = new MimeBodyPart(); 
        try {
        	// 附件 
	        mailContent.addBodyPart(attach); 
	         
	        // 附件1(利用jaf框架读取数据源生成邮件体) 
	        DataSource dataSource = new FileDataSource(new File(path)); 
	        DataHandler dataHandler = new DataHandler(dataSource); 
	        attach.setFileName(MimeUtility.encodeText(name)); 
	        attach.setDataHandler(dataHandler); 
		} catch (MessagingException e) {
			log.error("{}", e);
			
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsg = "添加附件失败." + path + "." + e.getMessage();
		} catch (UnsupportedEncodingException e) {
			log.error("{}", e);
			
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsg = "添加附件失败." + path + "." + e.getMessage();
		}
        return new ServiceResponse<MimeBodyPart>(errorNO, errorMsg, attach);
	}
	
}
