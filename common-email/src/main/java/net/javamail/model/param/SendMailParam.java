package net.javamail.model.param;

import lombok.Builder;
import lombok.Getter;

import javax.mail.internet.InternetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 编  号：
 * 名  称：SendMailParam
 * 描  述：发送邮件参数
 * 完成日期：2018/8/4 15:11
 * @author：felix.shao
 */
@Builder(toBuilder = true)
@Getter
public class SendMailParam {

	/** 邮件发送协议 */
	protected String protocol;
	
	/** SMTP邮件服务器 */
	protected String host;
	
	/** SMTP邮件服务器默认端口 */
	protected String port;
	
	/** 是否要求身份认证 */
	protected String isAuth;
	
	/** 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息） */
	protected String isEnabledDebugMod;
	
	/** 发件人 */
	protected InternetAddress sender;
	
	/** 发件人账号 */
	protected String account;
	
	/** 发件人密码 */
	protected String password;
	
	/** 邮件主题 */
	protected String subject;
	
	/** 发送时间*/
	protected Date sentDate;
	
	/** 收件人 */
	protected InternetAddress[] toRecipients;
	
	/** 抄送收件人 */
	protected InternetAddress[] ccRecipients;
	
	/** 密送收件人 */
	protected InternetAddress[] bccRecipients;
	
	/** 邮件正文 */
	protected String content;
	
	/** 发送内嵌图片 */
	protected List<EmbedImageParam> embedImages;
	
	/** 发送邮件附件 */
	protected List<AttachFileParam> attachFiles;
	
	/** 初始化连接邮件服务器的会话信息  */
	protected Properties props;
	
	/** 是否压缩附件.true:压缩;false:不压缩  */
	protected boolean zipFlag;
	
	/** 压缩附件参数  */
	protected ZipParam zipParam;
	
	/** 拓展参数,非发送邮件必要参数  */
	protected Map<String, String> exParams;
	
	/**
	 * 默认初始化常用的配置属性
	 * @Title: initDefaultProps
	 */
	public void initDefaultProps(){
		this.putProp("mail.transport.protocol", protocol);
		this.putProp("mail.smtp.host", host);
		this.putProp("mail.smtp.port", port);
		
		isAuth = (null == isAuth || "".endsWith(isAuth))? "true" : isAuth; //默认需要权限认证
		this.putProp("mail.smtp.auth", isAuth);
		
		isEnabledDebugMod = (null == isEnabledDebugMod || "".endsWith(isEnabledDebugMod))? "fasle" : isAuth; //默认不开启调试模式
		this.putProp("mail.debug", isEnabledDebugMod);
	}
	
	/**
	 * props可以用这个方法接受其他拓展参数
	 * @Title: putProp 
	 * @param key
	 * @param value
	 * @return
	 */
	public SendMailParam putProp(String key, String value){
		if(null == props){
			props = new Properties();
		}
		props.setProperty(key, value);
		return this;
	}

	public SendMailParam exParams(Map<String, String> exParams){
		this.exParams = exParams;
		return this;
	}

}
