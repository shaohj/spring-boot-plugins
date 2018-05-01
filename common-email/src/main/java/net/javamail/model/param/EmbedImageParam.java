package net.javamail.model.param;

/**
 * 发送邮件内嵌图片参数
 * 编  号：<br/>
 * 名  称：ImageAttachParam<br/>
 * 描  述：<br/>
 * 完成日期：2016年11月16日 上午10:03:13<br/>
 * 编码作者：ShaoHanJie<br/>
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
	 * 
	 * 编  号：<br/>
	 * 名  称：Builder<br/>
	 * 描  述：<br/>
	 * 完成日期：2016年11月14日 下午6:08:18<br/>
	 * 编码作者：ShaoHanJie<br/>
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
