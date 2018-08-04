package com.poi.template.excel.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.util.IOUtils;

/**
 * POI工具类
 * @author ShaoHj
 *
 */
public class POIUtils {

	 /**
     * 获取Cell值
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell){
    	Object result = null;
    	
    	switch (cell.getCellTypeEnum()) {
		case NUMERIC:
			result = cell.getNumericCellValue();break;
		case STRING:
			result = cell.getStringCellValue();break;
		case BOOLEAN:
			result = cell.getBooleanCellValue();break;
		case FORMULA:
			result = cell.getCellFormula();break;
		case BLANK:
			result = "";break;
		default:
			result = "";break;
		}
    	
    	return result;
    }
	
    /**
	 * 根据文件后缀过滤文件列表
	 * @param dirFile 目录文件
	 * @param format 文件后缀
	 * @return
	 */
	public static List<String> getFormatChildFiles(File dirFile, String format){
		if(!dirFile.isDirectory()){
			return null;
		}
		
		String[] children = dirFile.list(); //子文件列表
		
		List<String> excelFiles = null; //保存需要合并的excel文件
		
		//寻找该目录下的需要合并的excel文件
		if(null != children && children.length > 0){
			excelFiles = new Vector<String>();
			
			for(int i=0; i<children.length; i++){
				File cf = new File(children[i]);
				
				if(!cf.isDirectory() && format.equals(StringUtils.getStringSuffix(children[i]))){
					excelFiles.add(children[i]); //文件为需要合并的文件,添加至列表中
				}
			}
		}
		
		return excelFiles;
	}
	
	/**
	 * 将Workbook写入文件中
	 * @param wb
	 * @param filePath
	 * @throws IOException
	 */
	public static void writeFile(Workbook wb, String filePath) throws IOException {
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(filePath);
			wb.write(fos);
		} finally {
			IOUtils.closeQuietly(fos);
		}
	}
    
}
