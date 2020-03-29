package com.sprboot.plugin.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 编  号：
 * 名  称：AopLogApplication
 * 描  述：
 * 完成日期：2020/3/29 19:28
 * @author：felix.shao
 */
@SpringBootApplication
public class AopLogApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "aop");
        SpringApplication.run(AopLogApplication.class, args);
    }

}
