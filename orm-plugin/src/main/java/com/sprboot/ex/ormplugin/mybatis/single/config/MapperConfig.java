package com.sprboot.ex.ormplugin.mybatis.single.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 编  号：
 * 名  称：MybatisConfig
 * 描  述：
 * 完成日期：2020/04/04 19:47
 *
 * @author：felix.shao
 */
@Configuration
@MapperScan("com.sprboot.ex.ormplugin.mybatis.single.mapper")
public class MapperConfig {
}
