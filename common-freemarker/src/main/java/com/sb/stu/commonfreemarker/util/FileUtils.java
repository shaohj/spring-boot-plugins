package com.sb.stu.commonfreemarker.util;

import java.io.File;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 编  号：
 * 名  称：FileUtils
 * 描  述：
 * 完成日期：2018/8/4 15:40
 * @author：felix.shao
 */
@Slf4j
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

	public static boolean createFile(String filePath){
		createParentFolder(filePath);
		File file = new File(filePath);
		if(!file.exists()){
			try{
				return file.createNewFile();
			}catch (IOException e){
				log.error("", e);
			}
		}
		return false;
	}

	public static boolean deleteFile(String filePath){
		File file = new File(filePath);
		return deleteFile(file);
	}

	public static boolean deleteFile(File file){
		if(file.exists()){
			return file.delete();
		}
		return false;
	}

}
