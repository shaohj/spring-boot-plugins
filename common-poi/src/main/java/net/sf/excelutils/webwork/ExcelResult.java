/*
 * Copyright 2003-2005 ExcelUtils http://excelutils.sourceforge.net
 * Created on 2005-6-18
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

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelUtils;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.dispatcher.WebWorkResultSupport;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.util.OgnlValueStack;

/**
 * <p>
 * <b>ExcelResult </b> is a webwork's excel result
 * </p>
 * 
 * @author jokeway
 * @version $Revision: 1.8 $ $Date: 2005/11/01 07:44:44 $
 */
public class ExcelResult extends WebWorkResultSupport {

	private static final long serialVersionUID = 1L;

	// private static final Log log = LogFactory.getLog(ExcelResult.class);

	protected String contentType = "application/vnd.ms-excel";

	/**
	 * Execute this result, using the specified template location.
	 * <p/>
	 * The template location has already been interoplated for any variable
	 * substitutions
	 * <p/>
	 * this method obtains the excel template and the object wrapper from
	 * ValueStack.
	 */
	protected void doExecute(String location, ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();

		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + System.currentTimeMillis() + ".xls\"");
		InputStream in = null;
		try {
			OgnlValueStack stack = invocation.getStack();

			in = getTemplate(invocation, location, stack);

			OutputStream out = response.getOutputStream();

			Object context = ExcelManager.getInstance().buildContextObject(stack);
			ExcelUtils.export(location, in, context, out);

			out.flush();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	protected InputStream getTemplate(ActionInvocation invocation, String location, OgnlValueStack stack) {
		return ExcelManager.getInstance().getExcel(ServletActionContext.getServletContext(), location);
	}
}
