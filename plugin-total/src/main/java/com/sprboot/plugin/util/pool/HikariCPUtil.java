package com.sprboot.plugin.util.pool;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 编  号：
 * 名  称：HikariCPUtil
 * 描  述：HikariCPEx连接池工具类
 * 完成日期：2020/03/29 23:20
 *
 * @author：felix.shao
 */
@Slf4j
public class HikariCPUtil {

    public static ResultSet simpleQuery(String sql) {
        try {
            HikariDataSource dataSource = MyHikariDataSource.getDataSource();
            Connection connection = dataSource.getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()){
                log.info("\n-->result = {}", resultSet.getString(1));
            }

            if (connection != null && !connection.isClosed()){
                connection.close();
            }

            if (dataSource != null && !dataSource.isClosed()) {
                dataSource.close();
            }
            return resultSet;
        } catch (Exception e) {
            log.info("", e);
        }

        return null;
    }

    public static void main(String[] args) {
        simpleQuery("select 1");
    }

}
