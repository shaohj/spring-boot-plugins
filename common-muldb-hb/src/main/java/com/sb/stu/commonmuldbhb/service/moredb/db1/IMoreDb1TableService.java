package com.sb.stu.commonmuldbhb.service.moredb.db1;

import java.util.List;
import java.util.Optional;

import com.sb.stu.commonmuldbhb.model.db1.MoreDb1Table;

/**
 * 
 * 编  号：<br/>
 * 名  称：UserService<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:46:06<br/>
 * 编码作者：ShaoHj<br/>
 */
public interface IMoreDb1TableService {

    Optional<MoreDb1Table> findById(int id);

    MoreDb1Table findByNumber(String number);

    List<MoreDb1Table> findAllUserByPage(int page,int size);

    MoreDb1Table updateUser(MoreDb1Table user,boolean throwEx);

    void deleteUser(int id);
}
