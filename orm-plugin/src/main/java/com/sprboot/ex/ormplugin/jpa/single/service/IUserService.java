package com.sprboot.ex.ormplugin.jpa.single.service;

import com.sprboot.ex.ormplugin.jpa.single.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * 编  号：
 * 名  称：IUserService
 * 描  述：
 * 完成日期：2020/4/4 18:03
 * @author：felix.shao
 */
public interface IUserService {

    Optional<User> findById(int id);

    User findByCode(String code);

    Page<User> findAllUserByPage(int page, int size);

    User updateUser(User user, boolean throwEx);

    void deleteById(int id);
}
