package net.javamail.model.param;

/**
 * 编  号：
 * 名  称：AttachFileParam
 * 描  述：发送邮件附件参数
 * 完成日期：2018/8/4 15:10
 * @author：felix.shao
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
	 * 编  号：
	 * 名  称：AttachFileParam
	 * 描  述：
	 * 完成日期：2018/8/4 15:10
	 * @author：felix.shao
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
