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

import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.LazyDynaClass;

import com.opensymphony.xwork.util.OgnlValueStack;

/**
 * <p>
 * <b>OnglDynaClass</b> is a class which extends LazyDynaClass
 * </p>
 * 
 * @author rainsoft
 * @version $Revision: 1.7 $ $Date: 2005/07/12 06:03:20 $
 */
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
