package com.sprboot.plugin.emailex.util;

import com.alibaba.fastjson.util.IOUtils;
import com.sprboot.plugin.emailex.bean.ServiceResponse;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 编  号：
 * 名  称：EmlUtils
 * 描  述：eml文件辅助类
 * 完成日期：2020/3/29 19:23
 * @author：felix.shao
 */
@Slf4j
public class EmlUtils {
	
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
			log.error("{}", e);
			
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsg = e.getMessage();
        } finally {
			IOUtils.close(ein);
		}
		
		return new ServiceResponse<List<String>>(errorNO, errorMsg, pathList);
	}
	
}
