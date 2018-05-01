package com.sb.stu.commonaoplog.rest;

import java.util.HashMap;
import java.util.Map;

import com.sb.stu.commonaoplog.annotation.SystemControllerLog;
import com.sb.stu.commonaoplog.model.Demand;
import com.sb.stu.commonaoplog.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogController {

	public static final String exportPath = "C:/Users/dell/Desktop/temp/export";
	
	private @Autowired ILogService ls;
	
	public LogController(){
		System.out.println("**** LogController init ");
	}
	
	@GetMapping(path="/test", produces="application/json; charset=utf-8")
	public Object test(){
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("error_no", "0");
		result.put("error_msg", "ok!");
		
		return result;
	}

	/**
	 *  http://localhost:8080/aoplog/rest/logAop
	 *   {"did": 1}
	 *
	 * @param demand
	 * @return
	 */
	@PostMapping(path="/logAop", produces="application/json; charset=utf-8")
	@SystemControllerLog(description = "保存demand", name="logAop")
	public Object logAop(@RequestBody Demand demand){
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("error_no", "0");
		result.put("error_msg", "ok!");
		
		result.put("results", ls.save(demand));
		
		return result;
	}
	
}
