package com.sprboot.ex.ormplugin.jpa.muldb.service.db1;

import com.sprboot.ex.ormplugin.jpa.muldb.model.db1.User1;

import java.util.List;
import java.util.Optional;

/**
 * 编  号：
 * 名  称：User1Service
 * 描  述：
 * 完成日期：2020/4/4 18:30
 * @author：felix.shao
 */
public interface User1Service {

    Optional<User1> findById(int id);

    User1 findByCode(String code);

    List<User1> findAllUserByPage(int page, int size);

    User1 updateUser(User1 user, boolean throwEx);

    void deleteUser(int id);
}
