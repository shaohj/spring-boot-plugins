package com.sb.stu.commonpoi.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
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

	public void setUser(String user) {
		this.user = user;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getUser() {
		return user;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public double getQty() {
		return qty;
	}

	public List getChildren() {
		return children;
	}

	public String getField1() {
		return field1;
	}

	/**
	 * @return Returns the maps.
	 */
	public Map getMaps() {
		return maps;
	}

	/**
	 * @param maps
	 *            The maps to set.
	 */
	public void setMaps(Map maps) {
		this.maps = maps;
	}

	/**
	 * @return Returns the year.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * @param year
	 *            The year to set.
	 */
	public void setYear(String year) {
		this.year = year;
	}
}
