package com.sb.stu.npoi.sax07;

import com.alibaba.fastjson.JSON;
import com.sb.stu.npoi.common.bean.CellData;
import com.sb.stu.npoi.common.bean.RowData;
import com.sb.stu.npoi.common.bean.SheetData;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 编  号：
 * 名  称：Sax07ExcelUtil
 * 描  述：
 * 完成日期：2019/01/28 23:13
 *
 * @author：felix.shao
 */
@Slf4j
public class Sax07ExcelUtil {

    /**
     * 根据excel模板导出excel
     * @param tempFileName : 模板文件名
     * @param out : 输出文件流
     * @return void
     * @author SHJ
     * @since 2018/7/8 13:41
     */
    public static void export(String tempFileName, OutputStream out) {
        // 打开工作簿 并进行初始化
        XSSFWorkbook readWb = Sax07ExcelWorkbookUtil.openWorkbookByProPath(tempFileName);

        // 读取模板工作簿内容
        List<SheetData> readSheetDatas = readSheetData(readWb);

        log.info("\n读取的模板内容为-->\n{}", JSON.toJSONString(readSheetDatas));
    }

    /**
     * 读取XSSFWorkbook的Sheet，生成SheetData数据
     * @param readWb
     * @return
     */
    public static List<SheetData> readSheetData(XSSFWorkbook readWb){
        int readWbSheetCount = readWb.getNumberOfSheets(); //模板中所有sheet数量
        List<SheetData> sheetDatas = new ArrayList<>(readWbSheetCount);

        for (int i = 0; i < readWbSheetCount; i++) {
            Sheet readSheet = readWb.getSheetAt(i);

            SheetData sheetData = new SheetData();
            sheetData.setSheetNum(i);
            sheetData.setSheetName(readSheet.getSheetName());

            List<RowData> readSheetRowDatas = readRowData(readSheet, readSheet.getFirstRowNum(), readSheet.getLastRowNum());
            sheetData.setRowDatas(readSheetRowDatas);

            sheetDatas.add(sheetData);
        }

        return sheetDatas;
    }

    public static List<RowData> readRowData(Sheet readSheet, int firstRowNum, int lastRowNum){
        List<RowData> rowDatas = new ArrayList<>(lastRowNum - firstRowNum + 1);
        int curRowNum = firstRowNum;

        while (curRowNum <= lastRowNum) {
            Row readRow = readSheet.getRow(curRowNum);

            RowData rowData = new RowData();
            rowData.setRowNum(curRowNum);

            List<CellData> readRowCellDatas = null;
            if(null == readRow){
                readRowCellDatas = new ArrayList<>(0);
            } else {
                readRowCellDatas = readCellData(readRow, readRow.getFirstCellNum(), readRow.getLastCellNum());
            }

            rowData.setCellDatas(readRowCellDatas);

            rowDatas.add(rowData);

            curRowNum++;
        }

        return rowDatas;
    }

    public static List<CellData> readCellData(Row readRow, int firstCellNum, int lastCellNum){
        List<CellData> cellDatas = new ArrayList<>(lastCellNum - firstCellNum + 1);

        int curCellNum = firstCellNum;

        while (curCellNum <= lastCellNum) {
            Cell readCell = readRow.getCell(curCellNum);

            CellData cellData = new CellData();
            cellData.setColNum(curCellNum);

            if (null == readCell){
                cellData.setValue("");
            } else {
                cellData.setValue(getCellValue(readCell));
            }

            cellDatas.add(cellData);

            curCellNum++;
        }

        return cellDatas;
    }

    public static Object getCellValue(Cell cell){
        Object result = null;
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                result = cell.getBooleanCellValue(); break;
            case FORMULA:
                result = cell.getCellFormula(); break;
            case NUMERIC:
                result = cell.getNumericCellValue(); break;
            case STRING:
                result = cell.getStringCellValue(); break;
            default:
        }
        return result;
    }

}
