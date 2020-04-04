package com.sprboot.ex.ormplugin.mybatis.single;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 编  号：
 * 名  称：MybatisSingleApplication
 * 描  述：
 * 完成日期：2020/4/4 19:39
 * @author：felix.shao
 */
@SpringBootApplication(
        exclude={HibernateJpaAutoConfiguration.class}
)
public class MybatisSingleApplication {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "mybatis-single");
        SpringApplication.run(MybatisSingleApplication.class, args);
    }
}
