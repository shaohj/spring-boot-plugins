package net.javamail.model.param;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.InternetAddress;

/**
 * 下载邮件参数
 * 编  号：<br/>
 * 名  称：DownMailParam<br/>
 * 描  述：<br/>
 * 完成日期：2018年3月23日 下午2:16:13<br/>
 * 编码作者：shj<br/>
 */
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
	
	public DownMailParam(){
		
	}
	
	/**
	 * 默认初始化常用的配置属性
	 * @Title: initDefaultProps
	 */
	public void initDefaultProps(){
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
	public DownMailParam protocol(String protocol){
		this.protocol = protocol;
		return this;
	}
	
	/**
	 * 设置邮箱类型
	 * @Title: type 
	 * @param type
	 * @return
	 */
	public DownMailParam type(String type){
		this.type = type;
		return this;
	}
	
	/**
	 * 设置SMTP邮件服务器
	 * @Title: host 
	 * @param host
	 * @return
	 */
	public DownMailParam host(String host){
		this.host = host;
		return this;
	}
	
	/**
	 * 设置接收邮件服务器
	 * @Title: recHost 
	 * @param recHost
	 * @return
	 */
	public DownMailParam recHost(String recHost){
		this.recHost = recHost;
		return this;
	}
	
	/**
	 * 设置SMTP邮件服务器默认端口
	 * @Title: port 
	 * @param port
	 * @return
	 */
	public DownMailParam port(int port){
		this.port = port;
		return this;
	}
	
	/**
	 * 设置是否要求身份认证,不设置默认为true
	 * @Title: isAuth 
	 * @param isAuth
	 * @return
	 */
	public DownMailParam isAuth(String isAuth){
		this.isAuth = isAuth;
		return this;
	}
	
	/**
	 * 设置是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息,不设置默认为false
	 * @Title: isEnabledDebugMod 
	 * @param isEnabledDebugMod
	 * @return
	 */
	public DownMailParam isEnabledDebugMod(String isEnabledDebugMod){
		this.isEnabledDebugMod = isEnabledDebugMod;
		return this;
	}
	
	/**
	 * 设置发件人
	 * @Title: sender 
	 * @param sender
	 * @return
	 */
	public DownMailParam sender(InternetAddress sender){
		this.sender = sender;
		return this;
	}
	
	/**
	 * 设置发件人账号
	 * @Title: account 
	 * @param account
	 * @return
	 */
	public DownMailParam account(String account){
		this.account = account;
		return this;
	}
	
	/**
	 * 设置发件人密码
	 * @Title: password 
	 * @param password
	 * @return
	 */
	public DownMailParam password(String password){
		this.password = password;
		return this;
	}
	
	/**
	 * 设置邮件主题
	 * @Title: subject 
	 * @param subject
	 * @return
	 */
	public DownMailParam subject(String subject){
		this.subject = subject;
		return this;
	}
	
	/**
	 * 设置发送时间
	 * @Title: sentDate 
	 * @param sentDate
	 * @return
	 */
	public DownMailParam sentDate(Date sentDate){
		this.sentDate = sentDate;
		return this;
	}
	
	/**
	 * 设置收件人
	 * @Title: toRecipients 
	 * @param toRecipients
	 * @return
	 */
	public DownMailParam toRecipients(InternetAddress[] toRecipients){
		this.toRecipients = toRecipients;
		return this;
	}
	
	/**
	 * 设置抄送收件人
	 * @Title: ccRecipients 
	 * @param ccRecipients
	 * @return
	 */
	public DownMailParam ccRecipients(InternetAddress[] ccRecipients){
		this.ccRecipients = ccRecipients;
		return this;
	}
	
	/**
	 * 设置密送收件人
	 * @Title: bccRecipients 
	 * @param bccRecipients
	 * @return
	 */
	public DownMailParam bccRecipients(InternetAddress[] bccRecipients){
		this.bccRecipients = bccRecipients;
		return this;
	}
	
	/**
	 * 设置邮件正文
	 * @Title: content 
	 * @param content
	 * @return
	 */
	public DownMailParam content(String content){
		this.content = content;
		return this;
	}
	
	/**
	 * 设置发送邮件附件
	 * @Title: attachFiles 
	 * @param attachFiles
	 * @return
	 */
	public DownMailParam attachFiles(List<AttachFileParam> attachFiles){
		this.attachFiles = attachFiles;
		return this;
	}
	
	/**
	 * 设置初始化连接邮件服务器的会话信息
	 * @Title: props 
	 * @param props
	 * @return
	 */
	public DownMailParam props(Properties props){
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
	public DownMailParam putProp(String key, String value){
		if(null == props){
			props = new Properties();
		}
		props.setProperty(key, value);
		return this;
	}
	
	/**
	 * 设置下载文件存储路径
	 * @Title: path 
	 * @param path
	 * @return
	 */
	public DownMailParam path(String path){
		this.path = path;
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
		public static DownMailParam protocol(String protocol){
			DownMailParam params = new DownMailParam();
			return params.protocol(protocol);
		}
		
		/**
		 * 设置邮件类型
		 * @Title: type 
		 * @param type
		 * @return
		 */
		public static DownMailParam type(String type){
			DownMailParam params = new DownMailParam();
			return params.type(type);
		}
		
		/**
		 * 设置SMTP邮件服务器
		 * @Title: host 
		 * @param host
		 * @return
		 */
		public static DownMailParam host(String host){
			DownMailParam params = new DownMailParam();
			return params.host(host);
		}
		
		/**
		 * 设置接收服务器
		 * @Title: recHost 
		 * @param recHost
		 * @return
		 */
		public static DownMailParam recHost(String recHost){
			DownMailParam params = new DownMailParam();
			return params.recHost(recHost);
		}
		
		/**
		 * 设置SMTP邮件服务器默认端口
		 * @Title: port 
		 * @param port
		 * @return
		 */
		public static DownMailParam port(int port){
			DownMailParam params = new DownMailParam();
			return params.port(port);
		}
		
		/**
		 * 设置是否要求身份认证,默认为true,即要求身份认证
		 * @Title: isAuth 
		 * @param isAuth
		 * @return
		 */
		public static DownMailParam isAuth(String isAuth){
			DownMailParam params = new DownMailParam();
			return params.isAuth(isAuth);
		}
		
		/**
		 * 设置是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息,默认为false,即不启用
		 * @Title: isEnabledDebugMod 
		 * @param isEnabledDebugMod
		 * @return
		 */
		public static DownMailParam isEnabledDebugMod(String isEnabledDebugMod){
			DownMailParam params = new DownMailParam();
			return params.isEnabledDebugMod(isEnabledDebugMod);
		}
		
		/**
		 * 设置发件人
		 * @Title: sender 
		 * @param sender
		 * @return
		 */
		public static DownMailParam sender(InternetAddress sender){
			DownMailParam params = new DownMailParam();
			return params.sender(sender);
		}
		
		/**
		 * 设置发件人账号
		 * @Title: account 
		 * @param account
		 * @return
		 */
		public DownMailParam account(String account){
			DownMailParam params = new DownMailParam();
			return params.account(account);
		}
		
		/**
		 * 设置发件人密码
		 * @Title: password 
		 * @param password
		 * @return
		 */
		public DownMailParam password(String password){
			DownMailParam params = new DownMailParam();
			return params.password(password);
		}
		
		/**
		 * 设置邮件主题
		 * @Title: subject 
		 * @param subject
		 * @return
		 */
		public DownMailParam subject(String subject){
			DownMailParam params = new DownMailParam();
			return params.subject(subject);
		}
		
		/**
		 * 设置发送时间
		 * @Title: sentDate 
		 * @param sentDate
		 * @return
		 */
		public DownMailParam sentDate(Date sentDate){
			DownMailParam params = new DownMailParam();
			return params.sentDate(sentDate);
		}
		
		/**
		 * 设置收件人
		 * @Title: toRecipients 
		 * @param toRecipients
		 * @return
		 */
		public DownMailParam toRecipients(InternetAddress[] toRecipients){
			DownMailParam params = new DownMailParam();
			return params.toRecipients(toRecipients);
		}
		
		/**
		 * 设置
		 * @Title: ccRecipients 
		 * @param ccRecipients
		 * @return
		 */
		public DownMailParam ccRecipients(InternetAddress[] ccRecipients){
			DownMailParam params = new DownMailParam();
			return params.ccRecipients(ccRecipients);
		}
		
		/**
		 * 设置抄送收件人
		 * @Title: bccRecipients 
		 * @param bccRecipients
		 * @return
		 */
		public DownMailParam bccRecipients(InternetAddress[] bccRecipients){
			DownMailParam params = new DownMailParam();
			return params.bccRecipients(bccRecipients);
		}
		
		/**
		 * 设置密送收件人
		 * @Title: content 
		 * @param content
		 * @return
		 */
		public static DownMailParam content(String content){
			DownMailParam params = new DownMailParam();
			return params.content(content);
		}
		
		/**
		 * 设置发送内嵌图片
		 * @Title: attachFiles 
		 * @param attachFiles
		 * @return
		 */
		public static DownMailParam attachFiles(List<AttachFileParam> attachFiles){
			DownMailParam params = new DownMailParam();
			return params.attachFiles(attachFiles);
		}
		
		/**
		 * 设置发送邮件附件
		 * @Title: props 
		 * @param props
		 * @return
		 */
		public static DownMailParam props(Properties props){
			DownMailParam params = new DownMailParam();
			return params.props(props);
		}
		
		/**
		 * props可以用这个方法接受其他拓展参数
		 * @Title: putProp 
		 * @param key
		 * @param value
		 * @return
		 */
		public static DownMailParam putProp(String key, String value){
			DownMailParam params = new DownMailParam();
			return params.putProp(key, value);
		}
		
		/**
		 * 设置文件存储路径
		 * @Title: path 
		 * @param path
		 * @return
		 */
		public static DownMailParam path(String path){
			DownMailParam params = new DownMailParam();
			return params.path(path);
		}
		
	}


	public String getProtocol() {
		return protocol;
	}

	public String getType() {
		return type;
	}

	public String getHost() {
		return host;
	}

	public String getRecHost() {
		return recHost;
	}

	public int getPort() {
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

	public List<AttachFileParam> getAttachFiles() {
		return attachFiles;
	}

	public Properties getProps() {
		return props;
	}

	public String getPath() {
		return path;
	}

}
