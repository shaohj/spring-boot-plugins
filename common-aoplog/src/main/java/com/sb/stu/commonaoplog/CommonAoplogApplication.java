package com.sb.stu.commonaoplog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 编  号：
 * 名  称：CommonAoplogApplication
 * 描  述：
 * 完成日期：2018/8/4 13:14
 * @author：felix.shao
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
