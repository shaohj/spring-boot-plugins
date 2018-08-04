package net.javamail.model.param;

/**
 * 编  号：
 * 名  称：EmbedImageParam
 * 描  述：发送邮件内嵌图片参数
 * 完成日期：2018/8/4 15:11
 * @author：felix.shao
 */
public class EmbedImageParam {

	/** 文件路径 */
	protected String path;
	
	/** 内容编号,用于其它邮件体引用  */
	protected String contentId;
	
	public EmbedImageParam(){
		
	}
	
	public EmbedImageParam path(String path){
		this.path = path;
		return this;
	}
	
	public EmbedImageParam contentId(String contentId){
		this.contentId = contentId;
		return this;
	}
	
	/**
	 * 编  号：
	 * 名  称：EmbedImageParam
	 * 描  述：
	 * 完成日期：2018/8/4 15:11
	 * @author：felix.shao
	 */
	public static class Builder {
		
		public static EmbedImageParam path(String path){
			EmbedImageParam params = new EmbedImageParam();
			return params.path(path);
		}
		
		public static EmbedImageParam contentId(String contentId){
			EmbedImageParam params = new EmbedImageParam();
			return params.contentId(contentId);
		}
		
	}

	public String getPath() {
		return path;
	}

	public String getContentId() {
		return contentId;
	}

}
