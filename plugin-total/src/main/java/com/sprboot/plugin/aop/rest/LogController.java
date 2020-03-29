package com.sprboot.plugin.aop.rest;

import com.sprboot.plugin.aop.annotation.SystemControllerLog;
import com.sprboot.plugin.aop.bean.Demand;
import com.sprboot.plugin.aop.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 编  号：
 * 名  称：LogController
 * 描  述：
 * 完成日期：2020/3/29 18:56
 * @author：felix.shao
 */
@Slf4j
@RestController
public class LogController {

	@Autowired
	private ILogService iLogService;
	
	public LogController(){
		log.info("**** LogController init ");
	}

	/**
	 * http://localhost:8080/aoplog/test
	 * @return
	 */
	@GetMapping(path="/test", produces="application/json; charset=utf-8")
	public Object test(){
		Map<String, Object> result = new HashMap<String, Object>(4);
		
		result.put("error_no", "0");
		result.put("error_msg", "ok!");
		
		return result;
	}

	/**
	 *  http://localhost:8080/aoplog/logAop?did=1
	 *
	 * @param demand
	 * @return
	 */
	@GetMapping(path="/logAop", produces="application/json; charset=utf-8")
	@SystemControllerLog(description = "保存demand", name="logAop")
	public Object logAop(Demand demand){
		Map<String, Object> result = new HashMap<String, Object>(6);
		
		result.put("error_no", "0");
		result.put("error_msg", "ok!");
		
		result.put("results", iLogService.save(demand));
		
		return result;
	}
	
}
