package net.javamail.model.param;

/**
 * 发送邮件附件参数
 * 编  号：<br/>
 * 名  称：ImageAttachParam<br/>
 * 描  述：<br/>
 * 完成日期：2016年11月16日 上午10:03:13<br/>
 * 编码作者：ShaoHanJie<br/>
 */
public class AttachFileParam {

	/** 文件路径 */
	protected String path;
	
	/** 文件名  */
	protected String name;
	
	public AttachFileParam(){
		
	}
	
	public AttachFileParam path(String path){
		this.path = path;
		return this;
	}
	
	public AttachFileParam name(String name){
		this.name = name;
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
		
		public static AttachFileParam path(String path){
			AttachFileParam params = new AttachFileParam();
			return params.path(path);
		}
		
		public static AttachFileParam name(String name){
			AttachFileParam params = new AttachFileParam();
			return params.name(name);
		}
		
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

}
