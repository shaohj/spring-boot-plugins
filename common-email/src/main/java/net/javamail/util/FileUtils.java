package net.javamail.util;

import java.io.File;

import org.springframework.util.StringUtils;

/**
 * 编  号：
 * 名  称：FileUtils
 * 描  述：文件工具类
 * 完成日期：2018/8/4 15:13
 * @author：felix.shao
 */
public class FileUtils {

	/**
	 * 创建文件夹
	 * @Title: createDirs 
	 * @param uri 注意,这里是要传文件目录路径
	 * @return
	 */
	public static boolean createDirs(String uri){
		boolean flag = false;
		
		if(StringUtils.isEmpty(uri)){
			return flag;
		}
		
		File file = new File(uri);
		
		if(!file.exists()){
			flag = file.mkdirs();
		}
		
		return flag;
	}
	
	/**
	 * 创建文件或文件夹的父目录文件夹
	 * @Title: createParentDirs 
	 * @param uri 文件或文件夹路径
	 * @return
	 */
	public static boolean createParentDirs(String uri){
		boolean flag = false;
		
		if(StringUtils.isEmpty(uri)){
			return flag;
		}
		
		File file = new File(uri);
		
		if(!file.exists() && !StringUtils.isEmpty(file.getParent())){
			File parentFile = new File(file.getParent());
			
			if(!parentFile.exists()){
				parentFile.mkdirs();
			}
		}
		
		return flag;
	}
	
}
