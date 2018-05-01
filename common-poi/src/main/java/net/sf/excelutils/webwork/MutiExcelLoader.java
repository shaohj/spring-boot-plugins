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

/**
 * 
 * @author <a href="mailto:joke_way@yahoo.com.cn">jokeway</a>
 * @since 2005-10-10
 * @version $Revision: 1.1 $ $Date: 2005/10/10 07:09:37 $
 */
public class MutiExcelLoader implements ExcelLoader {

	private final ExcelLoader[] loaders;

	public MutiExcelLoader(ExcelLoader[] loaders) {
		this.loaders = (ExcelLoader[]) loaders.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.sf.excelutils.webwork.ExcelLoader#getExcel(java.lang.String)
	 */
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
