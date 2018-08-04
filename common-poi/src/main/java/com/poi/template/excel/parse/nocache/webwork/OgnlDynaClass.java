package com.poi.template.excel.parse.nocache.webwork;

import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaClass;

import com.opensymphony.xwork.util.OgnlValueStack;

public class OgnlDynaClass extends LazyDynaClass {

	private static final long serialVersionUID = 1L;

	OgnlValueStack valueStack;

	/**
	 * Constructor
	 * 
	 * @param valueStack
	 */
	public OgnlDynaClass(OgnlValueStack valueStack) {
		super();
		this.valueStack = valueStack;
		setReturnNull(true);
	}

	/**
	 * getDynaProperty
	 * 
	 * @param name
	 * @return DynaProperty
	 */
	public DynaProperty getDynaProperty(String name) {
		DynaProperty property = super.getDynaProperty(name);
		if (null == property) {
			Object value = valueStack.findValue(name);
			if (null != value) {
				property = new DynaProperty(name, value.getClass());
			}
		}
		return property;
	}
}
