package com.sprboot.ex.ormplugin.mybatis.muldb.model.db2;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 编  号：
 * 名  称：User2
 * 描  述：
 * 完成日期：2020/4/4 19:18
 * @author：felix.shao
 */
@Data
public class User2 {

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
