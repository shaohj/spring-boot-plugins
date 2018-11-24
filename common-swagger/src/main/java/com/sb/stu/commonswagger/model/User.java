package com.sb.stu.commonswagger.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 编  号：
 * 名  称：User
 * 描  述：
 * 完成日期：2018/11/24 14:46
 * @author：felix.shao
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer userId;

    private String userName;

    private Integer age;

    private Integer sex;

    private LocalDateTime birthDay;

}
