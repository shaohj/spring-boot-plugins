package com.sb.stu.commonfreemarker.util;

import java.io.File;

import org.springframework.util.StringUtils;

/**
 * 编  号：
 * 名  称：FileUtils
 * 描  述：
 * 完成日期：2018/8/4 15:27
 * @author：felix.shao
 */
public class FileUtils {

	/**
	 * 创建父文件夹
	 * <br/>
	 * @Title: createParentFolder 
	 * @param filePath
	 * @return
	 */
	public static boolean createParentFolder(String filePath) {
		if(StringUtils.isEmpty(filePath)){
			return false;
		}
		File toFile = new File(filePath);
		
    	if(!toFile.exists() && !StringUtils.isEmpty(toFile.getParent())){
    		File parentFolder = new File(toFile.getParent());
    		
    		if(!parentFolder.exists()){
    			parentFolder.mkdirs();
    		}
    	}
    	
    	return true;
	}
	
}
