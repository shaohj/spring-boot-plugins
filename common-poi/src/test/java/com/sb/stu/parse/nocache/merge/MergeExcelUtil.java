package com.sb.stu.parse.nocache.merge;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.poi.template.excel.exception.ExcelException;
import com.poi.template.excel.nocache.*;
import com.poi.template.excel.parse.nocache.WorkbookUtils;
import com.poi.template.excel.parse.nocache.webwork.GenerateSequenceUtil;
import com.poi.template.excel.utils.FileUtils;
import com.poi.template.excel.utils.IOUtils;
import com.poi.template.excel.utils.POIUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class MergeExcelUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MergeExcelUtil.class);

	public static void main(String[] args) {
		String dir = "C:\\Users\\SHJ\\Desktop\\temp\\mergedir\\xlsx";
		String mergeFilePath = "C:\\Users\\SHJ\\Desktop\\temp\\mergedir\\" + GenerateSequenceUtil.generateSequenceNo() + ".xlsx";
		boolean isXlsx = true;
		
		try {
			mergeExcelFiles(dir, mergeFilePath, isXlsx);
			logger.info("合并导出成功");
		} catch (Exception e) {
			logger.error("", e.getMessage());
		}

	}
	
	/**
	 * 合并excel文件
	 * @param dir 指定该目录下所有的excel文件合并
	 * @param mergeFilePath 合并文件全路径
	 * @param isXlsx 指定合并的格式,由于复制样式时xls与xlsx样式不一致,因此只能要么合并xls文件,要么合并xlsx文件.true:合并xlsx文件
	 * @throws ExcelException
	 */
	public static void mergeExcelFiles(String dir, String mergeFilePath, boolean isXlsx) throws IllegalArgumentException, IOException, ExcelException {
		if(StringUtils.isEmpty(dir) || StringUtils.isEmpty(mergeFilePath)){
			throw new IllegalArgumentException("dir or mergeFilePath parameter can not be empty");
		}
		
		File file = new File(dir);
		
		if(file.isDirectory()){ //dir为一个目录才会继续进行合并操作
			String format = isXlsx ? "xlsx" : "xls";
			List<String> excelFiles = POIUtils.getFormatChildFiles(file, format); //保存需要合并的excel文件

			//合并excel
			if(CollectionUtils.isEmpty(excelFiles)){
				//dir参数不包含需要合并的excel文件,非法参数
				throw new IllegalArgumentException("there is no need to merge the excel file in this directory.dir:" + dir);
			}else{
				logger.debug("merge " + format + " file size is " + excelFiles.size() + ", dir:" + dir);
				
				if(excelFiles.size() == 1){
					FileUtils.copyFile(dir + File.separator + excelFiles.get(0), mergeFilePath); //单个文件复制即可
				}else{
					Workbook mergeWb = null;
					try{
						/* 从第1个文件开始合并文件,修改下sheet名称,防止sheet名称重复 */
						mergeWb = WorkbookUtils.openWorkbook(dir + File.separator + excelFiles.get(0), true);
						for(int i=0; i< mergeWb.getNumberOfSheets(); i++){
							mergeWb.setSheetName(i, mergeWb.getSheetName(i) + "_" + GenerateSequenceUtil.generateSequenceNo());
						}
						//后续文件依次与合并文件合并
						for(int i=1; i<excelFiles.size(); i++){
							copyExcel(dir + File.separator + excelFiles.get(i), mergeWb);
						}
						
						POIUtils.writeFile(mergeWb, mergeFilePath);
					} finally{
						IOUtils.close(mergeWb);
					}
				}
			}
		} else {
			//dir参数不为目录,非法参数
			throw new IllegalArgumentException("dir param is't a directory.dir:" + dir); 
		}
	}
	
	/**
	 * 复制excel至目标文件中
	 * @param fromFilePath
	 * @throws ExcelException
	 * @throws IOException 
	 */
	public static void copyExcel(String fromFilePath, Workbook toWb) throws ExcelException, IOException{
		Workbook formWb = null;
		try{
			formWb = WorkbookUtils.openWorkbook(fromFilePath, true);
			
			Iterator<Sheet> itor = formWb.iterator();
			
			while(itor.hasNext()){
				Sheet fromSheet = itor.next();
				
				//防止sheet名称重复
				Sheet toSheet = toWb.createSheet(fromSheet.getSheetName() + "_" +GenerateSequenceUtil.generateHhMmSsS());
				MergeExcelUtil.copySheet(formWb, toWb, fromSheet, toSheet);
			}
			
		} finally {
			IOUtils.close(formWb);
		}
	}
	
	/**
	 * 合并sheet
	 * @param fromWb
	 * @param toWb
	 * @param fromSheet
	 * @param toSheet
	 */
	public static void copySheet(Workbook fromWb, Workbook toWb,Sheet fromSheet, Sheet toSheet) {
        if(null == fromSheet.getRow(fromSheet.getFirstRowNum())){
        	return; //若为空的sheet,跳过复制
        }
    	
    	mergeSheetAllRegion(fromSheet, toSheet); 
        //设置列宽,最少设置10个列宽,尽量防止第1行只有1列,其它行有多列情况
    	int columnNum = fromSheet.getRow(fromSheet.getFirstRowNum()).getLastCellNum();
    	columnNum = (columnNum >= 10) ? columnNum : 10;
        for(int i=0; i<=columnNum; i++){ 
            toSheet.setColumnWidth(i,fromSheet.getColumnWidth(i)); 
        } 
        for (Iterator<Row> rowIt = fromSheet.rowIterator(); rowIt.hasNext();) { 
            Row oldRow = rowIt.next(); 
            Row newRow = toSheet.createRow(oldRow.getRowNum()); 
            copyRow(fromWb, toWb,oldRow,newRow); 
        }  
    }
	
	/**
	 * 复制sheet中的row
	 * @param fromWb
	 * @param toWb
	 * @param oldRow
	 * @param toRow
	 */
	public static void copyRow(Workbook fromWb, Workbook toWb,Row oldRow,Row toRow){  
        toRow.setHeight(oldRow.getHeight());
        for (Iterator<Cell> cellIt = oldRow.cellIterator(); cellIt.hasNext();) { 
            Cell tmpCell = cellIt.next(); 
            
            CellStyle cs = toWb.createCellStyle();
            cs.cloneStyleFrom(tmpCell.getCellStyle());
            CellUtil.createCell(toRow, tmpCell.getColumnIndex(), String.valueOf(POIUtils.getCellValue(tmpCell)), cs);
        }  
    }

	/**
	 * 复制sheet中的合并单元格
	 * @param fromSheet
	 * @param toSheet
	 */
    public static void mergeSheetAllRegion(Sheet fromSheet, Sheet toSheet){ //合并单元格
        int num = fromSheet.getNumMergedRegions();
        CellRangeAddress cellR = null;
        for (int i = 0; i < num; i++) {
            cellR = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(cellR);
        }
    } 
    
}
