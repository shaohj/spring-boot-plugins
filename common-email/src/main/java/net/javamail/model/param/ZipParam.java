package net.javamail.model.param;

import lombok.Builder;
import lombok.Getter;

/**
 * 编  号：
 * 名  称：ZipParam
 * 描  述：压缩附件参数
 * 完成日期：2018/8/4 15:12
 * @author：felix.shao
 */
@Builder(toBuilder = true)
@Getter
public class ZipParam {

	/** 文件路径 */
	protected String path;
	
	/** 文件名  */
	protected String name;
	
}
