package com.sprboot.ex.ormplugin.jpa.muldb.service.db2.impl;

import com.sprboot.ex.ormplugin.jpa.muldb.dao.db2.User2Repository;
import com.sprboot.ex.ormplugin.jpa.muldb.model.db2.User2;
import com.sprboot.ex.ormplugin.jpa.muldb.service.db2.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 编  号：
 * 名  称：User2ServiceImpl
 * 描  述：
 * 完成日期：2020/4/4 18:27
 * @author：felix.shao
 */
@Service
@Transactional("secondTransactionManager")
public class User2ServiceImpl implements User2Service {

    @Autowired
    private User2Repository user2Repository;

    @Override
    public Optional<User2> findById(int id) {
        return user2Repository.findById(id);
    }

}
