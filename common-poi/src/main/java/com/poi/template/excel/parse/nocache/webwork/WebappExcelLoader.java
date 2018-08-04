package com.poi.template.excel.parse.nocache.webwork;

import java.io.InputStream;

import javax.servlet.ServletContext;

public class WebappExcelLoader implements ExcelLoader {
	private final ServletContext servletContext;

	public WebappExcelLoader(ServletContext context) {
		this.servletContext = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ExcelLoader#getExcel(java.lang.String)
	 */
	public InputStream getExcel(String location) {
		return servletContext.getResourceAsStream(location);
	}

}
