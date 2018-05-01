package com.sb.stu.commonjpahb.service.impl;

import com.sb.stu.commonjpahb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sb.stu.commonjpahb.dao.UserRepository;
import com.sb.stu.commonjpahb.service.IUserService;

import java.util.List;
import java.util.Optional;

/**
 * 
 * 编  号：<br/>
 * 名  称：UserServiceImpl<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:58<br/>
 * 编码作者：ShaoHj<br/>
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
