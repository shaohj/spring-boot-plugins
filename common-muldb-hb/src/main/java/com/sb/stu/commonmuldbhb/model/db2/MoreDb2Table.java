package com.sb.stu.commonmuldbhb.model.db2;

import javax.persistence.*;

/**
 * 
 * 编  号：<br/>
 * 名  称：MoreDb2Table<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月24日 下午1:45:38<br/>
 * 编码作者：ShaoHj<br/>
 */
@Entity
@Table(name="more_db2_table")
public class MoreDb2Table {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="number")
    private String number;

    @Column(name="price")
    private double price;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
