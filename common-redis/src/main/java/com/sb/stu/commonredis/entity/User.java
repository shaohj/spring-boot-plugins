package com.sb.stu.commonredis.entity;

import java.io.Serializable;

/**
 * 
 * 编  号：<br/>
 * 名  称：User<br/>
 * 描  述：<br/>
 * 完成日期：2018年3月14日 下午2:43:18<br/>
 * 编码作者：shj<br/>
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
