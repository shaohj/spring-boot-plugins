package net.javamail.model.param;

/**
 * 压缩附件参数
 * 编  号：<br/>
 * 名  称：ZipParam<br/>
 * 描  述：<br/>
 * 完成日期：2018年3月23日 下午2:30:14<br/>
 * 编码作者：shj<br/>
 */
public class ZipParam {

	/** 文件路径 */
	protected String path;
	
	/** 文件名  */
	protected String name;
	
	public ZipParam(){
		
	}
	
	public ZipParam path(String path){
		this.path = path;
		return this;
	}
	
	public ZipParam name(String name){
		this.name = name;
		return this;
	}
	
	
	/**
	 * 
	 * 编  号：<br/>
	 * 名  称：Builder<br/>
	 * 描  述：<br/>
	 * 完成日期：2018年3月23日 下午2:30:40<br/>
	 * 编码作者：shj<br/>
	 */
	public static class Builder {
		
		public static ZipParam path(String path){
			ZipParam params = new ZipParam();
			return params.path(path);
		}
		
		public static ZipParam name(String name){
			ZipParam params = new ZipParam();
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
