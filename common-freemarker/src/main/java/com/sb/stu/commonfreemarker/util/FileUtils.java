package com.sb.stu.commonfreemarker.util;

import java.io.File;

import org.springframework.util.StringUtils;

/**
 * 
 * 编  号：<br/>
 * 名  称：FileUtils<br/>
 * 描  述：<br/>
 * 完成日期：2017年8月21日 上午10:35:22<br/>
 * 编码作者：ShaoHj<br/>
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
