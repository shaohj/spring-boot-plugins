package com.sb.stu.commonmuldbmb.model.db2;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 * 编  号：<br/>
 * 名  称：MoreDb2Table<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:38<br/>
 * 编码作者：ShaoHj<br/>
 */
public class MoreDb2Table {

    private Integer id;

    private String number;

    private Long price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
