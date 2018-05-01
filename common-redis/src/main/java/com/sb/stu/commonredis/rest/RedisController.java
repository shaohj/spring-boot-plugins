package com.sb.stu.commonredis.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

	/**
	 * http://localhost:8080/redis/rest/user
	 * @return
	 */
	@GetMapping(path="user")
	public Object getUser(){
		return "redis，你好";
	}
	
}
