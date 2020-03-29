package com.sprboot.plugin.emailex.event;

import com.sprboot.plugin.emailex.bean.ServiceResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 编  号：
 * 名  称：SendDownCallEventDefault
 * 描  述：默认的异步回调事件
 * 完成日期：2020/3/29 19:15
 * @author：felix.shao
 */
@Slf4j
public class SendDownCallEventDefault implements ISendDownCallEvent{

	@Override
	public void handle(ServiceResponse<String> serviceResponse, Map<String, String> params) {
		log.info("SendDownCallEventDefault...");
	}

}
