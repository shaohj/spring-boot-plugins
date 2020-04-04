package com.sprboot.plugin.redisuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 编  号：
 * 名  称：RedisUseApplication
 * 描  述：redis使用demo
 * 完成日期：2020/4/4 12:37
 * @author：felix.shao
 */
@SpringBootApplication
public class RedisUseApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "redisuse");
        SpringApplication.run(RedisUseApplication.class, args);
    }

}
