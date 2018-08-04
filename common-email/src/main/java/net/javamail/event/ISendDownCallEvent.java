package net.javamail.event;

import java.util.Map;

import net.javamail.ServiceResponse;

/**
 * 编  号：
 * 名  称：ISendDownCallEvent
 * 描  述：
 * 完成日期：2018/8/4 13:32
 * @author：felix.shao
 */
public interface ISendDownCallEvent {

	/**
	 * handle
	 *   异步发送邮件回调事件
	 * @param serviceResponse :
	 * @param params : 回调自定义拓展参数
	 * @return void
	 * @author felix.shao
	 * @since 2018/8/4 13:32
	 */
	public void handle(ServiceResponse<String> serviceResponse,
			Map<String, String> params);
	
}
