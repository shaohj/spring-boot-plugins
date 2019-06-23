package com.sb.stu.commonaoplog.annotation;

import java.lang.annotation.*;

/**
 * 编  号：
 * 名  称：SystemControllerLog
 * 描  述：
 * 完成日期：2019/6/23 12:29
 * @author：felix.shao
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {

	String description() default "";
	String name() default "";
	
}