package net.javamail.model.param;

import lombok.Builder;
import lombok.Getter;

import javax.mail.internet.InternetAddress;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 编  号：
 * 名  称：DownMailParam
 * 描  述：下载邮件参数
 * 完成日期：2018/8/4 15:11
 * @author：felix.shao
 */
@Builder(toBuilder = true)
@Getter
public class DownMailParam {

	/** 邮件发送协议 */
	protected String protocol;
	
	/** 邮箱类型:如pop3 */
	protected String type;
	
	/** SMTP邮件服务器 */
	protected String host;
	
	/** 收件服务器 */
	protected String recHost;
	
	/** 接收端口 */
	protected int port;
	
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
	
	/** 发送邮件附件 */
	protected List<AttachFileParam> attachFiles;
	
	/** 初始化连接邮件服务器的会话信息  */
	protected Properties props;
	
	/** 下载文件存储路径 */
	protected String path;

	/**
	 * 默认初始化常用的配置属性
	 * @Title: initDefaultProps
	 */
	public void initDefaultProps(){
		//默认需要权限认证
		isAuth = (null == isAuth || "".endsWith(isAuth))? "true" : isAuth;
		this.putProp("mail.smtp.auth", isAuth);

		//默认不开启调试模式
		isEnabledDebugMod = (null == isEnabledDebugMod || "".endsWith(isEnabledDebugMod))? "fasle" : isAuth;
		this.putProp("mail.debug", isEnabledDebugMod);
	}
	
	/**
	 * props可以用这个方法接受其他拓展参数
	 * @Title: putProp 
	 * @param key
	 * @param value
	 * @return
	 */
	public DownMailParam putProp(String key, String value){
		if(null == props){
			props = new Properties();
		}
		props.setProperty(key, value);
		return this;
	}

}
