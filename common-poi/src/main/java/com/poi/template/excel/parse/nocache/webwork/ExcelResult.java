package com.poi.template.excel.parse.nocache.webwork;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.poi.template.excel.parse.nocache.ExcelUtils;

import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.dispatcher.WebWorkResultSupport;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.util.OgnlValueStack;

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
