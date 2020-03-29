package com.sprboot.plugin.util.pool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;

/**
 * 编  号：
 * 名  称：DataSourceConfig
 * 描  述：
 * 完成日期：2020/03/29 23:23
 *
 * @author：felix.shao
 */
public class MyHikariDataSource {

    public static HikariDataSource getDataSource() throws SQLException {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://192.168.37.100:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false");
        config.setUsername("root");
        config.setPassword("root");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }

}
