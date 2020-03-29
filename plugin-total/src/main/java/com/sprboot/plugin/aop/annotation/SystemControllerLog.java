package com.sprboot.plugin.aop.annotation;

import java.lang.annotation.*;

/**
 * 编  号：
 * 名  称：SystemControllerLog
 * 描  述：aop日志注解
 * 完成日期：2020/3/29 18:52
 * @author：felix.shao
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {

	String description() default "";

	String name() default "";
	
}