package com.sb.stu.commonswagger.annotation;

import com.sb.stu.commonswagger.config.Swagger2CustConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 编  号：
 * 名  称：EnableCustSwagger2
 * 描  述：
 * 完成日期：2018/11/24 14:46
 * @author：felix.shao
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({Swagger2CustConfiguration.class})
public @interface EnableCustSwagger2 {

}
