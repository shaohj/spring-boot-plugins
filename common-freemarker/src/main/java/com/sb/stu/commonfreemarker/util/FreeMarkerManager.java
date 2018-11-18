package com.sb.stu.commonfreemarker.util;

import java.io.File;
import java.io.IOException;

import com.sb.stu.commonfreemarker.constants.FmTempFilePathEnum;
import com.sb.stu.commonfreemarker.constants.Osinfo;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;

import static com.sb.stu.commonfreemarker.constants.FreemarkConstants.*;

/**
 * 编  号：
 * 名  称：FreeMarkerManager
 * 描  述：
 * 完成日期：2018/11/18 11:35
 * @author：felix.shao
 */
public class FreeMarkerManager {

	/** freemark模板文件在类路径下的配置 */
	private static volatile Configuration classPathCfg;

	/** freemark模板文件在本地真实路径下的配置 */
	private static volatile Configuration realPathCfg;

	/**
	 * singleConfiguration
	 * @param fmEnum :
	 * @return freemarker.template.Configuration
	 * @throws IOException 一般是模板文件不存在等异常
	 * @author felix.shao
	 * @since 2018/11/18 21:20
	 */
	public static Configuration singleConfiguration(FmTempFilePathEnum fmEnum) throws IOException {
		Configuration configuration = null;
		switch (fmEnum){
			case REAL_PATH : configuration = singleRealPathCfg(); break;
			case CLASS_PATH : configuration = singleClassPathCfg(); break;
			default:  ;break;
		}
		return configuration;
	}

	/**
	 * 类路径下的文件每次修改,服务器都需要重启,不推荐该方法
	 * @return
	 * @author felix.shao
	 * @since 2018/11/18 21:19
	 */
	private static Configuration singleClassPathCfg() {
		if(null == classPathCfg){
			synchronized (FreeMarkerManager.class){
				if(null == classPathCfg){
					//定义模板的位置，从类路径相对FreeMarkerManager所在的模板加载路径
					TemplateLoader tempLoader = new ClassTemplateLoader(FreeMarkerManager.class, "");

						try { //web-inf:路径读取备份

							//file:/D:/workSpaces/ws1/springBootFreemarker/target/classes/
					        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
					        path += "templates/ftl";
					        String ftlPath = "";
					        if("windows".equalsIgnoreCase(Osinfo.getOSname().toString())){
					        	//window路径处理
					        	ftlPath = path.replace("file:", "").replace("classes\\", "").replace('/', '\\').substring(1).replace("%20", " ");
					        }else{
					        	//linux路径处理
					        	ftlPath = path.replace("file:", "").replace("classes/", "");
					        }

							File file = new File(ftlPath);
							tempLoader = new FileTemplateLoader(file);
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
		}

		return classPathCfg;
	}

	/**
	 * 指定路径加载,替换模板文件后不会重启服务器,推荐使用
	 * @return
	 * @author felix.shao
	 * @since 2018/11/18 21:19
	 */
	private static Configuration singleRealPathCfg() throws IOException {
		if(null == realPathCfg){
			synchronized (FreeMarkerManager.class){
				if(null == realPathCfg){
					realPathCfg = new Configuration(VERSION);

					String path = WORD_TEMP_BASE_REALPATH + FTL_PATH;
					File file = new File(path);
					//定义加载真实路径下的模板文件
					TemplateLoader tempLoader = new FileTemplateLoader(file);

					realPathCfg.setTemplateLoader(tempLoader);
					realPathCfg.setNumberFormat("0");
					//设置对象的包装器
					realPathCfg.setObjectWrapper(new DefaultObjectWrapper(VERSION));
					//设置异常处理器	${a.b.c.d}即使没有属性也不会出错
					realPathCfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
					realPathCfg.setDefaultEncoding("UTF-8");
				}
			}
		}

		return realPathCfg;
	}

}
