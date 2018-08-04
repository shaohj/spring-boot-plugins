package com.sb.stu.commonredis.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 编  号：
 * 名  称：RedisController
 * 描  述：
 * 完成日期：2018/8/4 15:30
 * @author：felix.shao
 */
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
