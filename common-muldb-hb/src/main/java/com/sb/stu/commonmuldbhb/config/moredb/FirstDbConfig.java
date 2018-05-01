package com.sb.stu.commonmuldbhb.config.moredb;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * 
 * 编  号：<br/>
 * 名  称：FirstDbConfig<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:02<br/>
 * 编码作者：ShaoHj<br/>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.sb.stu.commonmuldbhb.dao.db1",
	entityManagerFactoryRef = "firstEntityManagerFactory",
	transactionManagerRef="firstTransactionManager")
public class FirstDbConfig {

    /**
     * 数据源配置对象
     * Primary 表示默认的对象，Autowire可注入，不是默认的得明确名称注入
     * @return
     */
    @Bean
    @Primary //表示该bean为此类型的默认bean，在其他地方引用的时候用@Autowired即可按照类型注入，不受同类型多个对象影响
    @ConfigurationProperties("db.first.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * 数据源对象
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties("db.first.datasource")
    public DataSource firstDataSource() {
        return firstDataSourceProperties().initializeDataSourceBuilder().build();
    }

    /**
     * 实体管理对象
     * @param builder 由spring注入这个对象，首先根据type注入（多个就取声明@Primary的对象），否则根据name注入
     * @return
     */
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(firstDataSource())
                .packages("com.sb.stu.commonmuldbhb.model.db1")
                .persistenceUnit("firstDb")
                .build();
    }

    /**
     * 事务管理对象
     * @return
     */
    @Bean(name = "firstTransactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(firstDataSource());
    }

    @Bean
    @Primary
    public TransactionTemplate transactionTemplate(PlatformTransactionManager platformTransactionManager){
        return new TransactionTemplate(platformTransactionManager);
    }
}
