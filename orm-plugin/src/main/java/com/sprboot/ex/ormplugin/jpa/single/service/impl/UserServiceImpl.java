package com.sprboot.ex.ormplugin.jpa.single.service.impl;

import com.sprboot.ex.ormplugin.jpa.single.dao.UserRepository;
import com.sprboot.ex.ormplugin.jpa.single.model.User;
import com.sprboot.ex.ormplugin.jpa.single.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 编  号：
 * 名  称：UserServiceImpl
 * 描  述：
 * 完成日期：2020/4/4 18:03
 * @author：felix.shao
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository mdb1TableRepo;

    @Override
    public Optional<User> findById(int id) {
        return mdb1TableRepo.findById(id);
    }

    @Override
    public User findByCode(String code) {
        return mdb1TableRepo.findFirstByCode(code);
    }

    @Override
    public Page<User> findAllUserByPage(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<User> users =  this.mdb1TableRepo.findAll(pageable);
        return users;
    }

    @Override
    public User updateUser(User user, boolean throwEx) {
        User userNew = this.mdb1TableRepo.save(user);
        if(throwEx){
            throw new RuntimeException("throw a ex");
        }
        return userNew;
    }

    @Override
    public void deleteById(int id) {
        this.mdb1TableRepo.deleteById(id);
    }
}
