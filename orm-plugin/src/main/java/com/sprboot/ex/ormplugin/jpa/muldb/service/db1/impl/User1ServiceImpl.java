package com.sprboot.ex.ormplugin.jpa.muldb.service.db1.impl;

import com.sprboot.ex.ormplugin.jpa.muldb.dao.db1.User1Repository;
import com.sprboot.ex.ormplugin.jpa.muldb.model.db1.User1;
import com.sprboot.ex.ormplugin.jpa.muldb.service.db1.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 编  号：
 * 名  称：User1ServiceImpl
 * 描  述：
 * 完成日期：2020/4/4 18:28
 * @author：felix.shao
 */
@Service
public class User1ServiceImpl implements User1Service {

    @Autowired
    private User1Repository user1Repository;

    @Override
    public Optional<User1> findById(int id) {
        return user1Repository.findById(id);
    }

    @Override
    public User1 findByCode(String code) {
        return user1Repository.findFirstByCode(code);
    }

    @Override
    public List<User1> findAllUserByPage(int page,int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<User1> users =  user1Repository.findAll(pageable);
        return users.getContent();
    }

    @Override
    public User1 updateUser(User1 user,boolean throwEx) {
        User1 userNew = user1Repository.save(user);
        if(throwEx){
            throw new RuntimeException("throw a ex");
        }
        return userNew;
    }

    @Override
    public void deleteUser(int id) {
        user1Repository.deleteUser(id);
    }
}
