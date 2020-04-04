package com.sprboot.ex.ormplugin.mybatis.muldb.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 编  号：
 * 名  称：SecondDbConfig
 * 描  述：数据源2
 * 完成日期：2020/4/4 19:05
 * @author：felix.shao
 */
@Configuration
@MapperScan(basePackages = "com.sprboot.ex.ormplugin.mybatis.muldb.mapper.db2", sqlSessionTemplateRef = "seconddbSqlSessionTemplate")
public class SecondDbConfig {

	@Bean
	@ConfigurationProperties("db.second.datasource")
	public DataSourceProperties secondDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "seconddbDataSource")
	public DataSource secondDataSource() {
		return secondDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean(name = "seconddbSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("seconddbDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper-muldb/db2/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "seconddbTransactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("seconddbDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "seconddbSqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("seconddbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
