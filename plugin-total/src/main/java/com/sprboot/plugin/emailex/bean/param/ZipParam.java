package com.sprboot.plugin.emailex.bean.param;

import lombok.Builder;
import lombok.Getter;

/**
 * 编  号：
 * 名  称：ZipParam
 * 描  述：压缩附件参数
 * 完成日期：2020/3/29 19:20
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
