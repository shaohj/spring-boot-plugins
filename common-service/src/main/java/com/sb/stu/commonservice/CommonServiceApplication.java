package com.sb.stu.commonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 编  号：
 * 名  称：CommonServiceApplication
 * 描  述：
 * 完成日期：2018/8/4 15:32
 * @author：felix.shao
 */
@SpringBootApplication(
        exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class} //启动时排除了数据源
)
public class CommonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonServiceApplication.class, args);
    }
}
