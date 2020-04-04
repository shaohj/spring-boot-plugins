package com.sprboot.plugin.redisuse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 编  号：
 * 名  称：User
 * 描  述：
 * 完成日期：2020/4/4 12:38
 * @author：felix.shao
 */
@Data
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    private String username;

    private Integer age;

}
