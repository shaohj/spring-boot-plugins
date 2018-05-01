/*
 * Copyright 2003-2005 ExcelUtils http://excelutils.sourceforge.net
 * Created on 2005-7-11
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.excelutils.webwork;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.LazyDynaBean;

import com.opensymphony.xwork.util.OgnlValueStack;

/**
 * <p>
 * <b>OnglDynaBean</b> is a class which extends LazyDynaBean
 * </p>
 * 
 * @author rainsoft
 * @version $Revision: 1.4 $ $Date: 2005/10/10 07:09:37 $
 */
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
