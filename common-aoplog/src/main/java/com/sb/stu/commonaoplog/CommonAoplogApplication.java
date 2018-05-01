package com.sb.stu.commonaoplog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * 参考：
 *  (1)didispace-SpringBoot-Learning-master 4.2.4小节
 *  (2)Spring AOP 注解方式实现的一些"坑": http://www.jianshu.com/p/def4c497571c
 * 名  称：CommonAoplogApplication<br/>
 * 描  述：<br/>
 * 完成日期：2018年4月29日 下午5:21:23<br/>
 * 编码作者：ShaoHj<br/>
 */
@SpringBootApplication
public class CommonAoplogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonAoplogApplication.class, args);
    }

    // spring 跨域问题CORS，不配置，默认是支持的，不知道这个是否有用(https://blog.csdn.net/pinebud55/article/details/60874725)
    //@Bean
    //public FilterRegistrationBean corsFilter() {
    //    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //    CorsConfiguration config = new CorsConfiguration();
    //    config.setAllowCredentials(true);
    //    config.addAllowedOrigin("*");
    //    config.addAllowedHeader("*");
    //    config.addAllowedMethod("*");
    //    source.registerCorsConfiguration("/**", config);
    //    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    //    bean.setOrder(0);
    //    return bean;
    //}
}
