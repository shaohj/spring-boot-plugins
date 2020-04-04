package com.sprboot.ex.ormplugin.mybatis.muldb.mapper.db2;

import com.sprboot.ex.ormplugin.mybatis.muldb.model.db2.User2;

/**
 * 编  号：
 * 名  称：User2Mapper
 * 描  述：
 * 完成日期：2020/4/4 19:15
 * @author：felix.shao
 */
public interface User2Mapper {

    User2 selectById(String id);
    
}