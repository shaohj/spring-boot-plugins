package com.sb.stu.commonmuldbhb.service.moredb.db1.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sb.stu.commonmuldbhb.dao.db1.MoreDb1TableRepository;
import com.sb.stu.commonmuldbhb.model.db1.MoreDb1Table;
import com.sb.stu.commonmuldbhb.service.moredb.db1.IMoreDb1TableService;

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
public class MoreDb1TableServiceImpl implements IMoreDb1TableService {

    @Autowired
    private MoreDb1TableRepository mdb1TableRepo;

    @Override
    public Optional<MoreDb1Table> findById(int id) {
        return this.mdb1TableRepo.findById(id);
    }

    @Override
    public MoreDb1Table findByNumber(String number) {
        return this.mdb1TableRepo.findFirstByNumber(number);
    }

    @Override
    public List<MoreDb1Table> findAllUserByPage(int page,int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<MoreDb1Table> users =  this.mdb1TableRepo.findAll(pageable);
        return users.getContent();
    }

    @Override
    public MoreDb1Table updateUser(MoreDb1Table user,boolean throwEx) {
        MoreDb1Table userNew = this.mdb1TableRepo.save(user);
        if(throwEx){
            throw new RuntimeException("throw a ex");
        }
        return userNew;
    }

    @Override
    public void deleteUser(int id) {
        this.mdb1TableRepo.deleteUser(id);
    }
}
