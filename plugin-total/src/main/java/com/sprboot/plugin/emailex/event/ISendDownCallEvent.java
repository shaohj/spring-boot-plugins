package com.sprboot.plugin.emailex.event;

import com.sprboot.plugin.emailex.bean.ServiceResponse;

import java.util.Map;

/**
 * 编  号：
 * 名  称：ISendDownCallEvent
 * 描  述：
 * 完成日期：2020/3/29 19:14
 * @author：felix.shao
 */
public interface ISendDownCallEvent {

	/**
	 * handle
	 *   异步发送邮件回调事件
	 * @param serviceResponse
	 * @param params 回调自定义拓展参数
	 */
	public void handle(ServiceResponse<String> serviceResponse,
					   Map<String, String> params);
	
}
