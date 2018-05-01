package net.javamail.model.param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.InternetAddress;

/**
 * 发送邮件参数
 * 编  号：<br/>
 * 名  称：SendMailParam<br/>
 * 描  述：<br/>
 * 完成日期：2018年3月23日 下午2:46:20<br/>
 * 编码作者：shj<br/>
 */
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
	
	public SendMailParam(){
		
	}
	
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
	 * 设置邮件发送协议
	 * @Title: protocol 
	 * @param protocol
	 * @return
	 */
	public SendMailParam protocol(String protocol){
		this.protocol = protocol;
		return this;
	}
	
	/**
	 * 设置SMTP邮件服务器
	 * @Title: host 
	 * @param host
	 * @return
	 */
	public SendMailParam host(String host){
		this.host = host;
		return this;
	}
	
	/**
	 * 设置SMTP邮件服务器默认端口
	 * @Title: port 
	 * @param port
	 * @return
	 */
	public SendMailParam port(String port){
		this.port = port;
		return this;
	}
	
	/**
	 * 设置是否要求身份认证,不设置默认为true
	 * @Title: isAuth 
	 * @param isAuth
	 * @return
	 */
	public SendMailParam isAuth(String isAuth){
		this.isAuth = isAuth;
		return this;
	}
	
	/**
	 * 设置是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息,不设置默认为false
	 * @Title: isEnabledDebugMod 
	 * @param isEnabledDebugMod
	 * @return
	 */
	public SendMailParam isEnabledDebugMod(String isEnabledDebugMod){
		this.isEnabledDebugMod = isEnabledDebugMod;
		return this;
	}
	
	/**
	 * 设置发件人
	 * @Title: sender 
	 * @param sender
	 * @return
	 */
	public SendMailParam sender(InternetAddress sender){
		this.sender = sender;
		return this;
	}
	
	/**
	 * 设置发件人账号
	 * @Title: account 
	 * @param account
	 * @return
	 */
	public SendMailParam account(String account){
		this.account = account;
		return this;
	}
	
	/**
	 * 设置发件人密码
	 * @Title: password 
	 * @param password
	 * @return
	 */
	public SendMailParam password(String password){
		this.password = password;
		return this;
	}
	
	/**
	 * 设置邮件主题
	 * @Title: subject 
	 * @param subject
	 * @return
	 */
	public SendMailParam subject(String subject){
		this.subject = subject;
		return this;
	}
	
	/**
	 * 设置发送时间
	 * @Title: sentDate 
	 * @param sentDate
	 * @return
	 */
	public SendMailParam sentDate(Date sentDate){
		this.sentDate = sentDate;
		return this;
	}
	
	/**
	 * 设置收件人
	 * @Title: toRecipients 
	 * @param toRecipients
	 * @return
	 */
	public SendMailParam toRecipients(InternetAddress[] toRecipients){
		this.toRecipients = toRecipients;
		return this;
	}
	
	/**
	 * 设置抄送收件人
	 * @Title: ccRecipients 
	 * @param ccRecipients
	 * @return
	 */
	public SendMailParam ccRecipients(InternetAddress[] ccRecipients){
		this.ccRecipients = ccRecipients;
		return this;
	}
	
	/**
	 * 设置密送收件人
	 * @Title: bccRecipients 
	 * @param bccRecipients
	 * @return
	 */
	public SendMailParam bccRecipients(InternetAddress[] bccRecipients){
		this.bccRecipients = bccRecipients;
		return this;
	}
	
	/**
	 * 设置邮件正文
	 * @Title: content 
	 * @param content
	 * @return
	 */
	public SendMailParam content(String content){
		this.content = content;
		return this;
	}
	
	/**
	 * 设置发送内嵌图片
	 * @Title: embedImages 
	 * @param embedImages
	 * @return
	 */
	public SendMailParam embedImages(List<EmbedImageParam> embedImages){
		this.embedImages = embedImages;
		return this;
	}
	
	/**
	 * 设置发送邮件附件
	 * @Title: attachFiles 
	 * @param attachFiles
	 * @return
	 */
	public SendMailParam attachFiles(List<AttachFileParam> attachFiles){
		this.attachFiles = attachFiles;
		return this;
	}
	
	/**
	 * 设置初始化连接邮件服务器的会话信息
	 * @Title: props 
	 * @param props
	 * @return
	 */
	public SendMailParam props(Properties props){
		this.props = props;
		return this;
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
	
	/**
	 * 是否压缩附件.true:压缩;false:不压缩 
	 * @Title: zipFlag 
	 * @param zipFlag
	 * @return
	 */
	public SendMailParam zipFlag(boolean zipFlag){
		this.zipFlag = zipFlag;
		return this;
	}
	
	/**
	 * 压缩附件参数
	 * @Title: zipParam 
	 * @param zipParam
	 * @return
	 */
	public SendMailParam zipParam(ZipParam zipParam){
		this.zipParam = zipParam;
		return this;
	}
	
	/**
	 * 拓展参数,非发送邮件必要参数
	 * @Title: exParams 
	 * @param exParams
	 * @return
	 */
	public SendMailParam exParams(Map<String, String> exParams){
		this.exParams = exParams;
		return this;
	}
	
	/**
	 * 
	 * 编  号：<br/>
	 * 名  称：Builder<br/>
	 * 描  述：<br/>
	 * 完成日期：2016年11月14日 下午6:08:18<br/>
	 * 编码作者：ShaoHanJie<br/>
	 */
	public static class Builder {
		
		/**
		 * 设置邮件发送协议
		 * @Title: protocol 
		 * @param protocol
		 * @return
		 */
		public static SendMailParam protocol(String protocol){
			SendMailParam params = new SendMailParam();
			return params.protocol(protocol);
		}
		
		/**
		 * 设置SMTP邮件服务器
		 * @Title: host 
		 * @param host
		 * @return
		 */
		public static SendMailParam host(String host){
			SendMailParam params = new SendMailParam();
			return params.host(host);
		}
		
		/**
		 * 设置SMTP邮件服务器默认端口
		 * @Title: port 
		 * @param port
		 * @return
		 */
		public static SendMailParam port(String port){
			SendMailParam params = new SendMailParam();
			return params.port(port);
		}
		
		/**
		 * 设置是否要求身份认证,默认为true,即要求身份认证
		 * @Title: isAuth 
		 * @param isAuth
		 * @return
		 */
		public static SendMailParam isAuth(String isAuth){
			SendMailParam params = new SendMailParam();
			return params.isAuth(isAuth);
		}
		
		/**
		 * 设置是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息,默认为false,即不启用
		 * @Title: isEnabledDebugMod 
		 * @param isEnabledDebugMod
		 * @return
		 */
		public static SendMailParam isEnabledDebugMod(String isEnabledDebugMod){
			SendMailParam params = new SendMailParam();
			return params.isEnabledDebugMod(isEnabledDebugMod);
		}
		
		/**
		 * 设置发件人
		 * @Title: sender 
		 * @param sender
		 * @return
		 */
		public static SendMailParam sender(InternetAddress sender){
			SendMailParam params = new SendMailParam();
			return params.sender(sender);
		}
		
		/**
		 * 设置发件人账号
		 * @Title: account 
		 * @param account
		 * @return
		 */
		public SendMailParam account(String account){
			SendMailParam params = new SendMailParam();
			return params.account(account);
		}
		
		/**
		 * 设置发件人密码
		 * @Title: password 
		 * @param password
		 * @return
		 */
		public SendMailParam password(String password){
			SendMailParam params = new SendMailParam();
			return params.password(password);
		}
		
		/**
		 * 设置邮件主题
		 * @Title: subject 
		 * @param subject
		 * @return
		 */
		public SendMailParam subject(String subject){
			SendMailParam params = new SendMailParam();
			return params.subject(subject);
		}
		
		/**
		 * 设置发送时间
		 * @Title: sentDate 
		 * @param sentDate
		 * @return
		 */
		public SendMailParam sentDate(Date sentDate){
			SendMailParam params = new SendMailParam();
			return params.sentDate(sentDate);
		}
		
		/**
		 * 设置收件人
		 * @Title: toRecipients 
		 * @param toRecipients
		 * @return
		 */
		public SendMailParam toRecipients(InternetAddress[] toRecipients){
			SendMailParam params = new SendMailParam();
			return params.toRecipients(toRecipients);
		}
		
		/**
		 * 设置
		 * @Title: ccRecipients 
		 * @param ccRecipients
		 * @return
		 */
		public SendMailParam ccRecipients(InternetAddress[] ccRecipients){
			SendMailParam params = new SendMailParam();
			return params.ccRecipients(ccRecipients);
		}
		
		/**
		 * 设置抄送收件人
		 * @Title: bccRecipients 
		 * @param bccRecipients
		 * @return
		 */
		public SendMailParam bccRecipients(InternetAddress[] bccRecipients){
			SendMailParam params = new SendMailParam();
			return params.bccRecipients(bccRecipients);
		}
		
		/**
		 * 设置密送收件人
		 * @Title: content 
		 * @param content
		 * @return
		 */
		public static SendMailParam content(String content){
			SendMailParam params = new SendMailParam();
			return params.content(content);
		}
		
		/**
		 * 设置邮件正文
		 * @Title: embedImages 
		 * @param embedImages
		 * @return
		 */
		public static SendMailParam embedImages(List<EmbedImageParam> embedImages){
			SendMailParam params = new SendMailParam();
			return params.embedImages(embedImages);
		}
		
		/**
		 * 设置发送内嵌图片
		 * @Title: attachFiles 
		 * @param attachFiles
		 * @return
		 */
		public static SendMailParam attachFiles(List<AttachFileParam> attachFiles){
			SendMailParam params = new SendMailParam();
			return params.attachFiles(attachFiles);
		}
		
		/**
		 * 设置发送邮件附件
		 * @Title: props 
		 * @param props
		 * @return
		 */
		public static SendMailParam props(Properties props){
			SendMailParam params = new SendMailParam();
			return params.props(props);
		}
		
		/**
		 * props可以用这个方法接受其他拓展参数
		 * @Title: putProp 
		 * @param key
		 * @param value
		 * @return
		 */
		public static SendMailParam putProp(String key, String value){
			SendMailParam params = new SendMailParam();
			return params.putProp(key, value);
		}
		
		/**
		 * 是否压缩附件.true:压缩;false:不压缩
		 * @Title: zipFlag 
		 * @param zipFlag
		 * @return
		 */
		public static SendMailParam zipFlag(boolean zipFlag){
			SendMailParam params = new SendMailParam();
			return params.zipFlag(zipFlag);
		}
		
		/**
		 * 压缩附件参数 
		 * @Title: props 
		 * @param props
		 * @return
		 */
		public static SendMailParam zipParam(ZipParam zipParam){
			SendMailParam params = new SendMailParam();
			return params.zipParam(zipParam);
		}
		
		/**
		 * 拓展参数,非发送邮件必要参数
		 * @Title: props 
		 * @param props
		 * @return
		 */
		public static SendMailParam exParams(Map<String, String> exParams){
			SendMailParam params = new SendMailParam();
			return params.exParams(exParams);
		}
		
	}

	public String getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getIsAuth() {
		return isAuth;
	}

	public String getIsEnabledDebugMod() {
		return isEnabledDebugMod;
	}
	

	public InternetAddress getSender() {
		return sender;
	}
	
	public String getAccount() {
		return account;
	}

	public String getPassword() {
		return password;
	}

	public String getSubject() {
		return subject;
	}

	public Date getSentDate() {
		return sentDate;
	}
	

	public InternetAddress[] getToRecipients() {
		return toRecipients;
	}

	public InternetAddress[] getCcRecipients() {
		return ccRecipients;
	}

	public InternetAddress[] getBccRecipients() {
		return bccRecipients;
	}

	public String getContent() {
		return content;
	}
	
	public List<EmbedImageParam> getEmbedImages() {
		return embedImages;
	}

	public List<AttachFileParam> getAttachFiles() {
		return attachFiles;
	}

	public Properties getProps() {
		return props;
	}

	public boolean isZipFlag() {
		return zipFlag;
	}

	public ZipParam getZipParam() {
		return zipParam;
	}

	public Map<String, String> getExParams() {
		return exParams;
	}
	
}
