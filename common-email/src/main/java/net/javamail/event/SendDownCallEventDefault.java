package net.javamail.event;

import lombok.extern.slf4j.Slf4j;
import net.javamail.ServiceResponse;

import java.util.Map;

/**
 * 编  号：
 * 名  称：SendDownCallEventDefault
 * 描  述：默认的异步回调事件
 * 完成日期：2018/8/4 13:33
 * @author：felix.shao
 */
@Slf4j
public class SendDownCallEventDefault implements ISendDownCallEvent{

	@Override
	public void handle(ServiceResponse<String> serviceResponse, Map<String, String> params) {
		log.info("SendDownCallEventDefault...");
	}

}
