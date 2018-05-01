package com.sb.stu.commonmuldbhb.service.moredb.db2.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sb.stu.commonmuldbhb.dao.db2.MoreDb2TableRepository;
import com.sb.stu.commonmuldbhb.model.db2.MoreDb2Table;
import com.sb.stu.commonmuldbhb.service.moredb.db2.IMoreDb2TableService;

import java.util.Optional;

/**
 *
 * 编  号：<br/>
 * 名  称：OrderServiceImpl<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:44:53<br/>
 * 编码作者：ShaoHj<br/>
 */
@Service
@Transactional("secondTransactionManager")
public class MoreDb2TableServiceImpl implements IMoreDb2TableService {

    private @Autowired MoreDb2TableRepository mdb2TableRepo;

    @Override
    public Optional<MoreDb2Table> findById(int id) {
        return mdb2TableRepo.findById(id);
    }

    @Override
    public MoreDb2Table updateOrder(MoreDb2Table order, boolean throwEx) {
        MoreDb2Table orderNew = this.mdb2TableRepo.save(order);
        if(throwEx){
            throw new RuntimeException("throw a ex");
        }
        return orderNew;
    }
}
