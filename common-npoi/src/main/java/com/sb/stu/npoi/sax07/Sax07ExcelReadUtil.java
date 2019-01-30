package com.sb.stu.npoi.sax07;

import com.sb.stu.npoi.common.bean.CellData;
import com.sb.stu.npoi.common.bean.RowData;
import com.sb.stu.npoi.common.bean.SheetData;
import com.sb.stu.npoi.common.util.CalculationUtil;
import com.sb.stu.npoi.common.util.ExcelCommonUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 编  号：
 * 名  称：Sax07ExcelReadUtil
 * 描  述：
 * 完成日期：2019/01/31 00:02
 *
 * @author：felix.shao
 */
public class Sax07ExcelReadUtil {

    /**
     * 读取XSSFWorkbook的Sheet，生成SheetData数据
     * @param readWb
     * @return
     */
    public static List<SheetData> readSheetData(XSSFWorkbook readWb){
        //模板中所有sheet数量
        int readWbSheetCount = readWb.getNumberOfSheets();
        List<SheetData> sheetDatas = new ArrayList<>(readWbSheetCount);

        for (int i = 0; i < readWbSheetCount; i++) {
            Sheet readSheet = readWb.getSheetAt(i);

            SheetData sheetData = new SheetData();
            sheetData.setSheetNum(i);
            sheetData.setSheetName(readSheet.getSheetName());

            int maxCellNum = ExcelCommonUtil.getMaxCellNum(readSheet);
            int[] cellWidths = ExcelCommonUtil.getCellWidths(readSheet, maxCellNum);
            sheetData.setCellWidths(cellWidths);

            Map<String, RowData> readSheetRowDatas = readRowData(readSheet, readSheet.getFirstRowNum(), readSheet.getLastRowNum());
            sheetData.setRowDatas(readSheetRowDatas);

            sheetDatas.add(sheetData);
        }

        return sheetDatas;
    }

    public static Map<String, RowData> readRowData(Sheet readSheet, int firstRowNum, int lastRowNum){
        Map<String, RowData> rowDatas = new LinkedHashMap<>(CalculationUtil.calMapCapacity(lastRowNum - firstRowNum + 1));
        int curRowNum = firstRowNum;

        while (curRowNum <= lastRowNum) {
            Row readRow = readSheet.getRow(curRowNum);

            RowData rowData = new RowData();
            rowData.setRowNum(curRowNum);

            if(null != readRow){
                //设置行高等属性
                rowData.setHeight(readRow.getHeight());
                rowData.setHeightInPoints(readRow.getHeightInPoints());
            }

            Map<String, CellData> readRowCellDatas = null != readRow ?
                    readCellData(readRow, readRow.getFirstCellNum(), readRow.getLastCellNum())
                    : new LinkedHashMap<>(0);

            rowData.setCellDatas(readRowCellDatas);

            rowDatas.put(String.valueOf(curRowNum), rowData);

            curRowNum++;
        }

        return rowDatas;
    }

    public static Map<String, CellData> readCellData(Row readRow, int firstCellNum, int lastCellNum){
        Map<String, CellData> cellDatas = new LinkedHashMap<>(CalculationUtil.calMapCapacity(lastCellNum - firstCellNum + 1));

        if(-1 == firstCellNum) {
            return cellDatas;
        }
        int curCellNum = firstCellNum;

        while (curCellNum <= lastCellNum) {
            Cell readCell = readRow.getCell(curCellNum);

            CellData cellData = new CellData();
            cellData.setColNum(curCellNum);

            if(null != readCell){
                //设置样式等属性
                cellData.setCellStyle(readCell.getCellStyle());
                cellData.setCellType(readCell.getCellTypeEnum());
            }

            if (null == readCell){
                cellData.setValue("");
            } else {
                cellData.setValue(ExcelCommonUtil.getCellValue(readCell));
            }

            cellDatas.put(String.valueOf(curCellNum), cellData);

            curCellNum++;
        }

        return cellDatas;
    }

}
