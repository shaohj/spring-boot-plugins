package com.sb.stu.commonaoplog.rest;

import java.util.HashMap;
import java.util.Map;

import com.sb.stu.commonaoplog.annotation.SystemControllerLog;
import com.sb.stu.commonaoplog.model.Demand;
import com.sb.stu.commonaoplog.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 编  号：
 * 名  称：LogController
 * 描  述：
 * 完成日期：2018/8/4 13:20
 * @author：felix.shao
 */
@RestController
public class LogController {

	public static final String EXPORT_PATH = "C:/Users/dell/Desktop/temp/export";
	
	private @Autowired ILogService ls;
	
	public LogController(){
		System.out.println("**** LogController init ");
	}
	
	@GetMapping(path="/test", produces="application/json; charset=utf-8")
	public Object test(){
		Map<String, Object> result = new HashMap<String, Object>(4);
		
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
		Map<String, Object> result = new HashMap<String, Object>(6);
		
		result.put("error_no", "0");
		result.put("error_msg", "ok!");
		
		result.put("results", ls.save(demand));
		
		return result;
	}
	
}
