package net.javamail.event;

import java.util.Map;

import net.javamail.ServiceResponse;

public interface ISendDownCallEvent {

	public void handle(ServiceResponse<String> serviceResponse,
			Map<String, String> params);
	
}
