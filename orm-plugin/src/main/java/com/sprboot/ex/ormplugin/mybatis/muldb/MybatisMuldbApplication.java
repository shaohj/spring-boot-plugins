package com.sprboot.ex.ormplugin.mybatis.muldb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 编  号：
 * 名  称：MybatisMuldbApplication
 * 描  述：
 * 完成日期：2020/4/4 19:17
 * @author：felix.shao
 */
@SpringBootApplication(
        exclude={HibernateJpaAutoConfiguration.class}
)
public class MybatisMuldbApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "mybatis-muldb");
        SpringApplication.run(MybatisMuldbApplication.class, args);
    }
}
