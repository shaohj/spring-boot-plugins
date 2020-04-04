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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 编  号：
 * 名  称：FirstDbConfig
 * 描  述： 支持多数据源配置，复制一份代码修改所有appdb即可<br>
 *  		以该数据源为主数据源。只能设置@Primary到一份数据源作为主数据源
 * 完成日期：2020/4/4 19:03
 * @author：felix.shao
 */
@Configuration
@MapperScan(basePackages = "com.sprboot.ex.ormplugin.mybatis.muldb.mapper.db1", sqlSessionTemplateRef = "firstdbSqlSessionTemplate")
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
	@Bean(name = "firstDbDataSource")
	@Primary
	public DataSource testDataSource() {
		return firstDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean(name = "firstdbSqlSessionFactory")
	@Primary
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("firstDbDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper-muldb/db1/*.xml"));
		return bean.getObject();
	}

	@Bean(name = "firstdbTransactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager(@Qualifier("firstDbDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "firstdbSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate testSqlSessionTemplate(
			@Qualifier("firstdbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
