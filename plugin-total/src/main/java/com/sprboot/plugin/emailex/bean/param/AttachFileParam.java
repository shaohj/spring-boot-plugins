package com.sprboot.plugin.emailex.bean.param;

import lombok.Builder;
import lombok.Getter;

/**
 * 编  号：
 * 名  称：AttachFileParam
 * 描  述：发送邮件附件参数
 * 完成日期：2020/3/29 19:16
 * @author：felix.shao
 */
@Builder(toBuilder = true)
@Getter
public class AttachFileParam {

	public static void main(String[] args) {
		AttachFileParam param = AttachFileParam.builder().path("").name("").build();
	}

	/** 文件路径 */
	protected String path;

	/** 文件名  */
	protected String name;

}
