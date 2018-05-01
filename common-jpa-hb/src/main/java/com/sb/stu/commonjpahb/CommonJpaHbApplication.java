package com.sb.stu.commonjpahb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //启用审计功能
public class CommonJpaHbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonJpaHbApplication.class, args);
    }
}
