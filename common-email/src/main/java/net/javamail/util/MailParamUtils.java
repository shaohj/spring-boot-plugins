package net.javamail.util;

import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.util.StringUtils;

import net.javamail.ServiceResponse;
import net.javamail.model.param.DownMailParam;
import net.javamail.model.param.SendMailParam;

/**
 * 编  号：
 * 名  称：MailParamUtils
 * 描  述：发送邮件工具类
 * 完成日期：2018/8/4 15:13
 * @author：felix.shao
 */
public class MailParamUtils {

	/**
	 * 验证发送邮件参数是否合理
	 * @Title: validateSendMailParam 
	 * @param param
	 * @return
	 */
	public static ServiceResponse<String> validateSendMailParam(SendMailParam param){
		int errorNO = ServiceResponse.SUCCESS;
		StringBuffer errorMsgBuf = new StringBuffer();
		
		if(StringUtils.isEmpty(param.getProtocol())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("protocol is null. ");
		}
		
		if(StringUtils.isEmpty(param.getHost())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("host is null. ");
		}
		
		if(StringUtils.isEmpty(param.getPort())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("port is null. ");
		}
		
		if(StringUtils.isEmpty(param.getIsAuth())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("isAuth is null. ");
		}
		
		if(null == param.getSender()){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("sender is null. ");
		}
		
		if(StringUtils.isEmpty(param.getAccount())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("account is null. ");
		}
		
		if(StringUtils.isEmpty(param.getPassword())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("password is null. ");
		}
		
		if(StringUtils.isEmpty(param.getSubject())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("subject is null. ");
		}
		
		if(StringUtils.isEmpty(param.getContent())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("content is null. ");
		}
		
		return new ServiceResponse<String>(errorNO, errorMsgBuf.toString());
	}
	
	/**
	 * 验证下载邮件参数是否合理
	 * @Title: validateSendMailParam 
	 * @param param
	 * @return
	 */
	public static ServiceResponse<String> validateDownMailParam(DownMailParam param){
		int errorNO = ServiceResponse.SUCCESS;
		StringBuffer errorMsgBuf = new StringBuffer();
		
		if(StringUtils.isEmpty(param.getProtocol())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("protocol is null. ");
		}
		
		if(StringUtils.isEmpty(param.getType())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("type is null. ");
		}

		if(StringUtils.isEmpty(param.getRecHost())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("recHost is null. ");
		}
		
		if(StringUtils.isEmpty(param.getPort())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("port is null. ");
		}
		
		if(StringUtils.isEmpty(param.getIsAuth())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("isAuth is null. ");
		}
		
		if(StringUtils.isEmpty(param.getAccount())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("account is null. ");
		}
		
		if(StringUtils.isEmpty(param.getPassword())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("password is null. ");
		}
		
		if(StringUtils.isEmpty(param.getPath())){
			errorNO = ServiceResponse.DEFAULT_FAIL;
			errorMsgBuf.append("path is null. ");
		}
		
		return new ServiceResponse<String>(errorNO, errorMsgBuf.toString());
	}
	
	/**
	 * 转InternetAddress[]工具函数
	 * @Title: parseInternetAddress 
	 * @param addressList
	 * @return
	 * @throws AddressException
	 */
	public static InternetAddress[] parseInternetAddress(List<String> addressList) throws AddressException{
		InternetAddress[] addressArr = null;
		if(null != addressList && addressList.size()>0){
			addressArr = new InternetAddress[addressList.size()];
			
			for(int i=0; i<addressList.size(); i++){
				InternetAddress address = new InternetAddress(addressList.get(i));
				addressArr[i] = address;
			}
		}
		return addressArr;
	}
	
}
