package com.sb.stu.commonmuldbmb.config.moredb;

import javax.sql.DataSource;

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

/**
 * 
 * @desc 数据源2
 * @author ShaoHj
 * @date 2018年5月1日 下午5:58:18
 * @version 1.0.0
 */
@Configuration
@MapperScan(basePackages = "com.sb.stu.commonmuldbmb.mapper.db2", sqlSessionTemplateRef = "seconddbSqlSessionTemplate")
public class SecondDbConfig {

	@Bean
	@ConfigurationProperties("db.second.datasource")
	public DataSourceProperties secondDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "seconddbDataSource")
	@ConfigurationProperties("db.second.datasource")
	public DataSource secondDataSource() {
		return secondDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean(name = "seconddbSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("seconddbDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/db2/*.xml"));
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
