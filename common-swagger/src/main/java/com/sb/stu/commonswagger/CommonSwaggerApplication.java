package com.sb.stu.commonswagger;

import com.sb.stu.commonswagger.annotation.EnableCustSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 编  号：
 * 名  称：CommonSwaggerApplication
 * 描  述：
 * swagger ui: http://localhost:8080/comswagger/rest/swagger-ui.html#/
 * 完成日期：2018/11/24 14:33
 * @author：felix.shao
 */
@SpringBootApplication(
        exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class} //启动时排除了数据源
)
@EnableCustSwagger2
public class CommonSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonSwaggerApplication.class, args);
    }

}
