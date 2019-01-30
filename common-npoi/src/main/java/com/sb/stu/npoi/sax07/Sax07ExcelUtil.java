package com.sb.stu.npoi.sax07;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.sb.stu.npoi.common.bean.CellData;
import com.sb.stu.npoi.common.bean.RowData;
import com.sb.stu.npoi.common.bean.SheetData;
import com.sb.stu.npoi.common.util.CalculationUtil;
import com.sb.stu.npoi.common.util.ExcelCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

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
    public static void export(String tempFileName, Map<String, Object> params, OutputStream out) {
        // 打开工作簿 并进行初始化
        XSSFWorkbook readWb = Sax07ExcelWorkbookUtil.openWorkbookByProPath(tempFileName);

        // 读取模板工作簿内容
        List<SheetData> readSheetDatas = Sax07ExcelReadUtil.readSheetData(readWb);

        // 写入模板内容
        //创建缓存的输出文件工作簿
        SXSSFWorkbook writeWb = new SXSSFWorkbook(100);

        writeSheetData(writeWb, params, readSheetDatas);

        try {
            //输出文件并清理导出的临时缓存文件
            writeWb.write(out);
            writeWb.dispose();
        } catch (IOException e) {
            log.info("导出excel时错误", e);
        }

        log.info("\n读取的模板内容为-->\n{}", JSON.toJSONString(readSheetDatas));
    }

    public static void writeSheetData(final SXSSFWorkbook writeWb, final Map<String, Object> params, final List<SheetData> readSheetDatas){
        if(CollUtil.isEmpty(readSheetDatas)){
            return;
        }

        readSheetDatas.stream().forEach(readSheetData -> {
            //创建sheet
            SXSSFSheet writeSheet =  writeWb.createSheet(readSheetData.getSheetName());

            if(!ArrayUtil.isEmpty(readSheetData.getCellWidths())){
                for (int i = 0; i < readSheetData.getCellWidths().length; i++) {
                    writeSheet.setColumnWidth(i, readSheetData.getCellWidths()[i]);
                }
            }

            if(CollUtil.isEmpty(readSheetData.getRowDatas())){
                return;
            }
            // 样式需要做缓存特殊处理，以sheet为单位作缓存处理，定义在此保证线程安全
            final Map<String, CellStyle> writeCellStyleCache = new HashMap<>();
            readSheetData.getRowDatas().forEach((readRowNum, rowData) -> writeRowData(writeWb, writeSheet, rowData, params, writeCellStyleCache));
        });
    }

    public static void writeRowData(final SXSSFWorkbook writeWb, final SXSSFSheet writeSheet, final RowData rowData,
                                    final Map<String, Object> params, final Map<String, CellStyle> writeCellStyleCache){
        final Row writeRow = writeSheet.createRow(rowData.getRowNum());
        writeRow.setHeight(rowData.getHeight());
        writeRow.setHeightInPoints(rowData.getHeightInPoints());
        if(CollUtil.isEmpty(rowData.getCellDatas())){
            return;
        }


        rowData.getCellDatas().forEach((readCellNum, cellData) -> writeCellData(writeWb, writeRow, rowData.getRowNum(), cellData, params, writeCellStyleCache));
    }

    public static void writeCellData(final SXSSFWorkbook writeWb, final Row writeRow, final int rowNum, final CellData cellData,
                                     final Map<String, Object> params, final Map<String, CellStyle> writeCellStyleCache){
        Cell writeCell = writeRow.createCell(cellData.getColNum());

        String cellStyleKey = rowNum + "_" + cellData.getColNum();
        CellStyle cellStyle = null;
        if(null != writeCellStyleCache.get(cellStyleKey)){
            cellStyle = writeCellStyleCache.get(cellStyleKey);
            writeCell.setCellStyle(cellStyle);
            writeCell.setCellType(cellData.getCellType());
        } else {
            if(null != cellData.getCellStyle()){
                cellStyle = writeWb.createCellStyle();
                cellStyle.cloneStyleFrom(cellData.getCellStyle());
                writeCellStyleCache.put(cellStyleKey, cellStyle);
                writeCell.setCellStyle(cellStyle);
                writeCell.setCellType(cellData.getCellType());
            }
        }

        Object parseValue = ExcelCommonUtil.parseTempStr(params, cellData.getValue());
        ExcelCommonUtil.setCellValue(writeCell, parseValue);
    }

}
