package com.sb.stu.npoi.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 编  号：
 * 名  称：ModelTest
 * 描  述：模拟模板导出的javabean实体类
 * 完成日期：2019/2/5 12:45
 * @author：felix.shao
 */
@Data
public class ModelTest {

	private int count;
	private String user;
	private String name;
	private double qty;
	private String year;

	private List children = new ArrayList();
	public String test1;
	private String field1;
	private Map maps = new HashMap();

	public ModelTest() {
	}

	public ModelTest(String name, String user, double qty) {
		this.name = name;
		this.user = user;
		this.qty = qty;
	}

}
