package com.poi.template.excel.parse.nocache.webwork;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.LazyDynaBean;

import com.opensymphony.xwork.util.OgnlValueStack;

@SuppressWarnings({ "rawtypes" })
public class OgnlDynaBean extends LazyDynaBean {
	// ~ Instance fields
	// ////////////////////////////////////////////////////////

	private static final long serialVersionUID = 1L;

	/**
	 * valueStack reference
	 */
	OgnlValueStack valueStack;

	// ~ Constructors
	// ///////////////////////////////////////////////////////////

	/**
	 * Constructs an instance of OgnlValueStackShadowMap.
	 * 
	 * @param valueStack
	 *            - the underlying valuestack
	 */
	public OgnlDynaBean(OgnlValueStack valueStack) {
		super(new OgnlDynaClass(valueStack));
		this.valueStack = valueStack;
	}

	// ~ Methods
	// ////////////////////////////////////////////////////////////////

	/**
	 * Implementation of get(), overriding LazyDynaBean implementation.
	 * 
	 * @param key
	 *            - The key to get in DynaBean and if not found there from the
	 *            valueStack.
	 * @return value - The object from DynaBean or if null, from the valueStack.
	 */
	public Object get(String key) {
		Object value = super.get(key);
		if (value != null) {
			if (value instanceof Map) {
				if (((Map) value).size() <= 0) {
					value = null;
				}
			}

			if (value instanceof List) {
				if (((List) value).size() <= 0) {
					value = null;
				}
			}
		}
		if ((value == null) && key != null) {
			value = valueStack.findValue(key);
		}

		return value;
	}

	public Object get(String name, String key) {
		Object value = super.get(name, key);
		if (value == null) {
			Object map = valueStack.findValue(name);
			if (map instanceof Map) {
				value = ((Map) map).get(key);
			}
		}
		return value;
	}

	public Object get(String name, int index) {
		Object value = super.get(name, index);
		if (value == null) {
			Object array = valueStack.findValue(name);
			if (array.getClass().isArray()) {
				return Array.get(array, index);
			} else if (array instanceof List) {
				return ((List) array).get(index);
			}
		}
		return value;
	}
}
