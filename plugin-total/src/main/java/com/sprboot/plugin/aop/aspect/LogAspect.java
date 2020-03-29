package com.sprboot.plugin.aop.aspect;

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
 * 描  述：aop切面
 * 完成日期：2020/3/29 18:54
 * @author：felix.shao
 */
@Slf4j
@Aspect
@Component
public class LogAspect {
	
	public LogAspect() {
		log.info("****  LogAspect  ******");
	}
	
	@Pointcut("@annotation(com.sprboot.plugin.aop.annotation.SystemControllerLog)")
    public void webLog(){}
    
	@Around("webLog()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
		log.info("**  around  begin  ");

    	Object result = jp.proceed();

		log.info("**  around  end  ");
		return result;
    }
    
}
