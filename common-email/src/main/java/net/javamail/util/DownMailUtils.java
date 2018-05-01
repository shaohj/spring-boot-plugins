package net.javamail.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import net.javamail.ServiceResponse;
import net.javamail.model.param.DownMailParam;

/**
 * 下载收件人指定的邮件
 * 编  号：<br/>
 * 名  称：DownMailUtils<br/>
 * 描  述：<br/>
 * 完成日期：2017年9月28日 上午10:55:05<br/>
 * 编码作者：shj<br/>
 */
public class DownMailUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DownMailUtils.class);
	
	/**
	 * 下载收件人指定的邮件
	 * @Title: downMultipleEmail 
	 * @param param
	 */
	public static ServiceResponse<List<String>> downMultipleEmail(DownMailParam param){
		ServiceResponse<String> validateResponse = MailParamUtils.validateDownMailParam(param);
		
		if(ServiceResponse.SUCCESS != validateResponse.getErrorNO()){
			return new ServiceResponse<List<String>>(validateResponse.getErrorNO(),
					validateResponse.getErrorMsg(), new ArrayList<String>(0));
		}
		
		int errorNO = ServiceResponse.SUCCESS;
		String errorMsg = "";
		List<String> pathList = new ArrayList<String>();
		
		/** 1. 创建Session实例对象 */
        Session session = Session.getInstance(param.getProps()); 
        
        /** 2. 获取 Store 并连接到服务器	*/
		URLName urlname = new URLName(param.getType(), param.getRecHost(),
				param.getPort(), null, param.getAccount(), param.getPassword());
		
		Store store = null;
		try {
			store = session.getStore(urlname);
			store.connect();
			
			Folder folder = store.getDefaultFolder();// 默认父目录
	        if (folder == null) {
	        	errorNO = ServiceResponse.DEFAULT_FAIL;
				errorMsg = "服务器不可用";
				
				return new ServiceResponse<List<String>>(errorNO, errorMsg, new ArrayList<String>(0));
	        }
	        
	        /** 3.获取收件箱,及收件箱可读邮件 */
	        Folder popFolder = folder.getFolder("INBOX");// 获取收件箱
            popFolder.open(Folder.READ_WRITE);// 可读邮件,可以删邮件的模式打开目录
            
            /** 4.获取所有收件箱下的所有邮件,对其满足条件的邮件进行下载操作 */
            Message[] messages = popFolder.getMessages();
            // 取出来邮件数
            int msgCount = popFolder.getMessageCount();
            
            logger.debug(param.getAccount() + "收件箱共有" + msgCount + "封邮件" );
            
            String tempPath = null;
            for (int i = 0; i < msgCount; i++) {
            	logger.debug("遍历第" + (i+1) + "封邮件");
            	
            	// 单个邮件
            	boolean flag = isMatcher(messages[i], param);
            	
            	logger.debug("第" + (i+1) + "封邮件是否满足匹配要求:" + String.valueOf(flag));
            	
            	if(flag){
            		String subject = messages[i].getSubject();
            		
            		subject = (subject.length()>20) ? subject.substring(0,20) : subject;
            		
            		tempPath = param.getPath() + "/" + subject + ".eml";
            		pathList.add(tempPath);
            		FileUtils.createParentDirs(tempPath);
            		messages[i].writeTo(new FileOutputStream(tempPath));
            	}
            }  
            
		} catch (MessagingException| IOException e) {
			logger.error("{}", e);
			
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsg = e.getMessage();
		} finally {
			try{store.close();} catch (MessagingException e) {}
		}
         
        return new ServiceResponse<List<String>>(errorNO, errorMsg, pathList);
	};
	
	/**
	 * 查看该收件信息是否匹配<br />
	 * 比较发件人邮箱地址、主题等信息
	 * @Title: isMatcher 
	 * @param msg
	 * @param param
	 * @return
	 */
	public static boolean isMatcher(Message msg, DownMailParam param) {
		boolean flag = false;
		
		try {
			/** 1 模糊比较主题 */
			if(!StringUtils.isEmpty(param.getSubject()) && !StringUtils.isEmpty(msg.getSubject())){
				flag = msg.getSubject().contains(param.getSubject());
				
				if(flag){
					return flag;
				}
			}
			
			msg.getSubject();
			
			/** 2 比较发件人信息 */
			Address[] froms = msg.getFrom();
			
			if(null != froms) {
			    InternetAddress addr = (InternetAddress)froms[0];
			    
			    if(null != param.getSender()){
			    	//比较发件人邮箱地址
			    	if(!StringUtils.isEmpty(param.getSender().getAddress())
			    			&& !StringUtils.isEmpty(addr.getAddress())){
			    		flag = addr.getAddress().equals(param.getSender().getAddress());
			    	}
			    	
			    	//精确比较发件人名称
			    	if(!flag){
			    		if(!StringUtils.isEmpty(param.getSender().getPersonal())
				    			&& !StringUtils.isEmpty(addr.getPersonal())){
				    		flag = addr.getPersonal().equals(param.getSender().getPersonal());
				    	}
			    	}
			    }
			}
			
		} catch (MessagingException e) {
			logger.error("{}", e);
		} 
		
		return flag;
	}
	
}
