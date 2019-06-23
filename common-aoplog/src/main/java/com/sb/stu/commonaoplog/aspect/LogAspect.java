package com.sb.stu.commonaoplog.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 编  号：
 * 名  称：LogAspect
 * 描  述：
 * 完成日期：2019/6/23 12:18
 * @author：felix.shao
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
	
	public LogAspect() {
		System.out.println("****  LogAspect  ******");
	}
	
	@Pointcut("@annotation(com.sb.stu.commonaoplog.annotation.SystemControllerLog)")
    public void webLog(){} // IRecordTheOperationLog rOptLog
    
	@Around("webLog()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
		log.info("**  around  begin  ");

    	Object[] args = jp.getArgs();
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
    	Object result = jp.proceed();

		log.info("**  around  end  ");
		return result;
    }
    
}
