package com.sprboot.ex.ormplugin.jpa.muldb.service.db2;

import com.sprboot.ex.ormplugin.jpa.muldb.model.db2.User2;

import java.util.Optional;

/**
 * 编  号：
 * 名  称：IMoreDb2TableService
 * 描  述：
 * 完成日期：2020/4/4 18:26
 * @author：felix.shao
 */
public interface User2Service {

    Optional<User2> findById(int id);

}
