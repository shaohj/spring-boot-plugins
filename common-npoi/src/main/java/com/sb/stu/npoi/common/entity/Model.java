package com.sb.stu.npoi.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Model {

	private int count;
	private String user;
	private String name;
	private double qty;
	private String year;

	private List children = new ArrayList();
	public String test1;
	private String field1;
	private Map maps = new HashMap();

	public Model() {
	}

	public Model(String name, String user, double qty) {
		this.name = name;
		this.user = user;
		this.qty = qty;
	}

}
