/*
 * Copyright 2003-2005 try2it.com.
 * Created on 2005-7-5
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

package net.sf.excelutils.tags;

import java.lang.reflect.Method;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import net.sf.excelutils.ExcelParser;
import net.sf.excelutils.ExcelUtils;

/**
 * <p>
 * <b>CallTag</b> is a class which parse the #call tag
 * </p>
 * 
 * @author rainsoft
 * @version $Revision: 1.1 $ $Date: 2005/07/06 01:43:39 $
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CallTag implements ITag {

	public static final String KEY_CALL = "#call";

	public int[] parseTag(Object context, Sheet sheet, Row curRow, Cell curCell) {

		String cellstr = curCell.getStringCellValue();
		if (null == cellstr || "".equals(cellstr)) {
			return new int[] { 0, 0, 0 };
		}

		cellstr = cellstr.substring(KEY_CALL.length()).trim();
		String serviceName = cellstr.substring(0, cellstr.indexOf('.'));
		String methodName = cellstr.substring(cellstr.indexOf('.') + 1, cellstr.indexOf('('));
		String paramStr = cellstr.substring(cellstr.indexOf('(') + 1, cellstr.lastIndexOf(')'));
		String propertyName = cellstr.substring(cellstr.lastIndexOf(')') + 1);

		Object[] params = new Object[0];
		Class[] types = new Class[0];
		// prepare params
		if (!"".equals(paramStr) && null != paramStr) {
			StringTokenizer st = new StringTokenizer(paramStr, ",");
			params = new Object[st.countTokens()];
			types = new Class[st.countTokens()];
			int index = 0;
			while (st.hasMoreTokens()) {
				String param = st.nextToken().trim();
				// get param type & value
				types[index] = getParamType(param);
				if ("java.lang.Object".equals(types[index].getName())) {
					params[index] = ExcelParser.parseStr(context, param);
					types[index] = params[index].getClass();
				} else if ("boolean".equals(types[index].getName())) {
					params[index] = Boolean.valueOf(param);
				} else if ("int".equals(types[index].getName())) {
					params[index] = Integer.valueOf(param);
				} else if ("double".equals(types[index].getName())) {
					params[index] = Double.valueOf(param);
				} else if ("java.lang.String".equals(types[index].getName())) {
					params[index] = param.substring(1, param.length() - 1);
				}
				index++;
			}
		}

		// get the service
		Object service = ExcelParser.getValue(context, serviceName);
		if (null == service) {
			return new int[] { 0, 0, 0 };
		}

		// get the method
		Method method = findMethod(service, methodName, types);
		if (null == method) {
			return new int[] { 0, 0, 0 };
		}

		// invoke method
		try {
			Object result = method.invoke(service, params);
			// put the result to context
			ExcelUtils.addValue(context, serviceName + methodName, result);
			curCell.setCellValue("${" + serviceName + methodName + propertyName + "}");
			ExcelParser.parseCell(context, sheet, curRow, curCell);
		} catch (Exception e) {

		}

		return new int[] { 0, 0, 0 };
	}

	public boolean hasEndTag() {
		return false;
	}

	public String getTagName() {
		return KEY_CALL;
	}

	private Method findMethod(Object service, String methodName, Class[] types) {
		Class clazz;
		if (service instanceof Class) {
			clazz = (Class) service;
		} else {
			clazz = service.getClass();
		}

		Method method = null;
		try {
			method = clazz.getDeclaredMethod(methodName, types);
		} catch (Exception e) {
		}
		return method;
	}

	private Class getParamType(String param) {
		if (param.startsWith("\"") && param.endsWith("\"")) {
			return String.class;
		} else if (param.indexOf(ExcelParser.VALUED_DELIM) >= 0) {
			return Object.class;
		} else if (param.equals("true") || param.equals("false")) {
			return boolean.class;
		} else if (param.indexOf('.') >= 0) {
			return double.class;
		} else {
			return int.class;
		}
	}
}
