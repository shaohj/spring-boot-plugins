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
package com.poi.template.excel.parse.nocache.webwork;

import java.io.InputStream;

public class MutiExcelLoader implements ExcelLoader {

	private final ExcelLoader[] loaders;

	public MutiExcelLoader(ExcelLoader[] loaders) {
		this.loaders = (ExcelLoader[]) loaders.clone();
	}

	public InputStream getExcel(String location) {
		if (loaders != null && loaders.length > 0) {
			for (int i = 0; i < loaders.length; i++) {
				InputStream st = loaders[i].getExcel(location);
				if (st != null) {
					return st;
				}
			}
		}
		return null;
	}

}
