package com.sprboot.ex.ormplugin.jpa.single.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 编  号：
 * 名  称：UserEntity
 * 描  述：原生sql查询返回具体实体类测试用
 * 完成日期：2020/4/4 18:02
 * @author：felix.shao
 */
@Data
public class UserEntity {

    private Integer id;

    private String code;

    private String name;

    private String createBy;

    private Date createTime;

    private String lastModifyBy;

    private Date lastModifyTime;

    private String deleteFlag;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
