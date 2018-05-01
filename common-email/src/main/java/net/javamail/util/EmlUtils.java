package net.javamail.util;

import java.io.*;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.util.IOUtils;

import net.javamail.ServiceResponse;

/**
 * eml文件辅助类
 * 编  号：<br/>
 * 名  称：ParseEmlUtils<br/>
 * 描  述：<br/>
 * 完成日期：2017年9月28日 下午3:32:26<br/>
 * 编码作者：shj<br/>
 */
public class EmlUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(EmlUtils.class);

	public static ServiceResponse<List<String>> parseAttachFiles(String emlPath, String storePath){
		int errorNO = ServiceResponse.SUCCESS;
		String errorMsg = "";
		List<String> pathList = new ArrayList<String>();
		
		InputStream ein = null;
		try {
            Session session = Session.getDefaultInstance(new Properties(), null);
            ein = new FileInputStream(emlPath);

            Message msg = new MimeMessage(session, ein);
            
            // getContent() 是获取包裹内容, Part相当于外包装
    		Object o = msg.getContent();
    		if(o instanceof Multipart) {
    			Multipart multipart = (Multipart) o ;
    			MessageUtils.reMultipart(multipart, storePath, pathList);
    		} else if (o instanceof Part){
    			Part part = (Part) o; 
    			MessageUtils.rePart(part, storePath, pathList);
    		} else {
    			errorMsg = "类型:" + msg.getContentType() + ",内容:" + msg.getContent(); 
    		}
        } catch (MessagingException|IOException e) {
        	logger.error("{}", e);
			
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsg = e.getMessage();
        } finally {
			IOUtils.close(ein);
		}
		
		return new ServiceResponse<List<String>>(errorNO, errorMsg, pathList);
	}
	
}
