package net.javamail.model.param;

/**
 * 编  号：
 * 名  称：ZipParam
 * 描  述：压缩附件参数
 * 完成日期：2018/8/4 15:12
 * @author：felix.shao
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
	 * 编  号：
	 * 名  称：ZipParam
	 * 描  述：
	 * 完成日期：2018/8/4 15:12
	 * @author：felix.shao
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
