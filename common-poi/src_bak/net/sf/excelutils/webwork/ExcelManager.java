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
import java.util.Hashtable;

import javax.servlet.ServletContext;

import net.sf.excelutils.ExcelUtils;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.webwork.config.Configuration;
import com.opensymphony.xwork.ObjectFactory;
import com.opensymphony.xwork.util.OgnlValueStack;

/**
 * @author <a href="mailto:joke_way@yahoo.com.cn">jokeway</a>
 * @since 2005-10-10
 * @version $Revision: 1.1 $ $Date: 2005/10/10 07:09:37 $
 */
public class ExcelManager {
	private static final Log log = LogFactory.getLog(ExcelManager.class);

	private static ExcelManager instance = null;

	protected ExcelLoader excelLoader = null;

	public final static synchronized ExcelManager getInstance() {
		if (instance == null) {
			String classname = ExcelManager.class.getName();

			if (Configuration.isSet("webwork.excel.manager.classname")) {
				classname = Configuration.getString("webwork.excel.manager.classname").trim();
			}

			try {
				log.info("Instantiating Excel ConfigManager!, " + classname);
				instance = (ExcelManager) ObjectFactory.getObjectFactory().buildBean(Class.forName(classname),
						new Hashtable<>());
			} catch (Exception e) {
				log.fatal("Fatal exception occurred while trying to instantiate a Excel ConfigManager instance, "
						+ classname, e);
			}
		}

		// if the instance creation failed, make sure there is a default
		// instance
		if (instance == null) {
			instance = new ExcelManager();
		}

		return instance;
	}

	public Object buildContextObject(OgnlValueStack stack) {
		DynaBean context = new OgnlDynaBean(stack);
		// add default to context
		try {
			DynaProperty properties[] = ExcelUtils.getContext().getDynaClass().getDynaProperties();
			for (int i = 0; i < properties.length; i++) {
				Object value = ExcelUtils.getContext().get(properties[i].getName());
				if (null != value) {
					context.set(properties[i].getName(), value);
				}
			}
		} catch (Exception e) {
		}
		return context;
	}

	protected ExcelLoader getExcelLoader(ServletContext context) {
		if (excelLoader == null) {
			excelLoader = new MutiExcelLoader(new ExcelLoader[] { new WebappExcelLoader(context) });
		}
		return excelLoader;
	}

	public InputStream getExcel(ServletContext context, String location) {
		return getExcelLoader(context).getExcel(location);
	}

}
