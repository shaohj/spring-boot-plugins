package com.sprboot.ex.ormplugin.mybatis.single.mapper;

import com.sprboot.ex.ormplugin.mybatis.single.model.User1;

/**
 * 编  号：
 * 名  称：User1Mapper
 * 描  述：
 * 完成日期：2020/4/4 19:01
 * @author：felix.shao
 */
public interface User1Mapper {
	
    User1 selectById(String id);
    
}