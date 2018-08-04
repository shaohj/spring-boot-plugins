package com.sb.stu.commonredis.entity;

import java.io.Serializable;

/**
 * 编  号：
 * 名  称：User
 * 描  述：
 * 完成日期：2018/8/4 15:40
 * @author：felix.shao
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    private String username;
    private Integer age;

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
