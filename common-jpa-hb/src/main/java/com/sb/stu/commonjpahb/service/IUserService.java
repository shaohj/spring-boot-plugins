package com.sb.stu.commonjpahb.service;

import java.util.List;
import java.util.Optional;

import com.sb.stu.commonjpahb.model.User;
import org.springframework.data.domain.Page;

/**
 * 
 * 编  号：<br/>
 * 名  称：UserService<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:46:06<br/>
 * 编码作者：ShaoHj<br/>
 */
public interface IUserService {

    Optional<User> findById(int id);

    User findByCode(String code);

    Page<User> findAllUserByPage(int page, int size);

    User updateUser(User user, boolean throwEx);

    void deleteById(int id);
}
