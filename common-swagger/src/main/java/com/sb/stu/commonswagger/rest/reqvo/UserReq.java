package com.sb.stu.commonswagger.rest.reqvo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 编  号：
 * 名  称：UserRep
 * 描  述：
 * 完成日期：2018/11/24 14:55
 * @author：felix.shao
 */
@Data
@ApiModel("userReq")
public class UserReq {

    @ApiModelProperty(name="userId", value = "用户id", example = "10000")
    private Integer userId;

    @ApiModelProperty(name="userName", value = "用户名称", example = "张三")
    private String userName;

    @ApiModelProperty(name="age", value = "年龄", example = "18")
    private Integer age;

    @ApiModelProperty(name="sex", value = "性别", example = "1，即男")
    private Integer sex;

    @ApiModelProperty(name="birthDay", value = "生日", example = "2018-08-22 11:11:11")
    private LocalDateTime birthDay;

    @ApiModelProperty(name="desc", value = "描述", example = "desc")
    private String desc;

}
