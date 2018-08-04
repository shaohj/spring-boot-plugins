package net.javamail.event;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.javamail.ServiceResponse;

/**
 * 编  号：
 * 名  称：SendDownCallEventDefault
 * 描  述：默认的异步回调事件
 * 完成日期：2018/8/4 13:33
 * @author：felix.shao
 */
public class SendDownCallEventDefault implements ISendDownCallEvent{

	private static final Logger logger = LoggerFactory.getLogger(SendDownCallEventDefault.class);
	
	@Override
	public void handle(ServiceResponse<String> serviceResponse, Map<String, String> params) {
		logger.info("SendDownCallEventDefault...");
	}

}
