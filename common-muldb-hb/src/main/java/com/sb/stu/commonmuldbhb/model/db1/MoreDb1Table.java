package com.sb.stu.commonmuldbhb.model.db1;

import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 
 * 编  号：<br/>
 * 名  称：MoreDb1Table<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:30<br/>
 * 编码作者：ShaoHj<br/>
 */
@Entity
@EntityListeners({AuditingEntityListener.class})  
@Table(name="more_db1_table")
public class MoreDb1Table extends Object{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="number")
    private String number;

    @Column(name="name")
    private String name;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
	@Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
