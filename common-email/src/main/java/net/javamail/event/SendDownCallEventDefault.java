package net.javamail.event;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.javamail.ServiceResponse;

public class SendDownCallEventDefault implements ISendDownCallEvent{

	private static final Logger logger = LoggerFactory.getLogger(SendDownCallEventDefault.class);
	
	@Override
	public void handle(ServiceResponse<String> serviceResponse, Map<String, String> params) {
		logger.info("SendDownCallEventDefault...");
	}

}
