package com.sb.stu.commonaoplog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 编  号：
 * 名  称：LogAspect
 * 描  述：
 * 完成日期：2018/8/4 13:20
 * @author：felix.shao
 */
@Aspect
@Component
public class LogAspect {
	
	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(LogAspect.class);

	public LogAspect() {
		System.out.println("****  LogAspect  ******");
	}
	
	@Pointcut("@annotation(com.sb.stu.commonaoplog.annotation.SystemControllerLog)")
    public void webLog(){} // IRecordTheOperationLog rOptLog
    
    @SuppressWarnings("unused")
	@Around("webLog()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
		logger.info("**  around  begin  ");

    	Object[] args = jp.getArgs();
    	WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
    	Object result = jp.proceed();

		logger.info("**  around  end  ");
		return result;
    }
    
}
