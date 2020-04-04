package com.sprboot.ex.ormplugin.jpa.muldb;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //启用审计功能
@SpringBootApplication(
        exclude={MybatisAutoConfiguration.class}
)
public class JpaMuldbApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "jpa-muldb");
        SpringApplication.run(JpaMuldbApplication.class, args);
    }

}
