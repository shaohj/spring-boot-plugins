package net.javamail.model.param;

import lombok.Builder;
import lombok.Getter;

/**
 * 编  号：
 * 名  称：EmbedImageParam
 * 描  述：发送邮件内嵌图片参数
 * 完成日期：2018/8/4 15:11
 * @author：felix.shao
 */
@Builder(toBuilder = true)
@Getter
public class EmbedImageParam {

	/** 文件路径 */
	protected String path;
	
	/** 内容编号,用于其它邮件体引用  */
	protected String contentId;
	
}
