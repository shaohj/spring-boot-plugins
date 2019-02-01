package com.sb.stu.npoi.sax07;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.sb.stu.npoi.common.bean.CellData;
import com.sb.stu.npoi.common.bean.ReadSheetData;
import com.sb.stu.npoi.common.bean.RowData;
import com.sb.stu.npoi.common.bean.write.WriteBlock;
import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import com.sb.stu.npoi.common.bean.write.tag.ConstTagData;
import com.sb.stu.npoi.common.bean.write.tag.TagData;
import com.sb.stu.npoi.common.util.ExcelCommonUtil;
import com.sb.stu.npoi.common.util.write.SaxWriteUtil;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
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
        List<ReadSheetData> readReadSheetData = Sax07ExcelReadUtil.readSheetData(readWb);

        // 写入模板内容
        List<WriteSheetData> writeSheetDatas = SaxWriteUtil.parseSheetData(readReadSheetData);

        //创建缓存的输出文件工作簿
        SXSSFWorkbook writeWb = new SXSSFWorkbook(100);

        writeSheetData(writeWb, params, writeSheetDatas);

        try {
            //输出文件并清理导出的临时缓存文件
            writeWb.write(out);
            writeWb.dispose();
        } catch (IOException e) {
            log.info("导出excel时错误", e);
        }

        log.info("\n读取的模板内容为-->\n{}", JSON.toJSONString(readReadSheetData));
        log.info("\n写入的模板内容为-->\n{}", JSON.toJSONString(writeSheetDatas));
    }

    public static void writeSheetData(final SXSSFWorkbook writeWb, final Map<String, Object> params, final List<WriteSheetData> writeSheetDatas){
        if(CollUtil.isEmpty(writeSheetDatas)){
            return;
        }

        writeSheetDatas.stream().forEach(writeSheetData -> {
            //创建sheet
            SXSSFSheet writeSheet =  writeWb.createSheet(writeSheetData.getSheetName());

            if(!ArrayUtil.isEmpty(writeSheetData.getCellWidths())){
                for (int i = 0; i < writeSheetData.getCellWidths().length; i++) {
                    writeSheet.setColumnWidth(i, writeSheetData.getCellWidths()[i]);
                }
            }

            if(CollUtil.isEmpty(writeSheetData.getWriteBlocks())){
                return;
            }
            // 样式需要做缓存特殊处理，以sheet为单位作缓存处理，定义在此保证线程安全
            final Map<String, CellStyle> writeCellStyleCache = new HashMap<>();
            writeSheetData.getWriteBlocks().forEach((readRowNum, writeBlock) -> writeBlock(writeWb, writeSheet, writeBlock, params, writeCellStyleCache));
        });
    }

    public static void writeBlock(final SXSSFWorkbook writeWb, final SXSSFSheet writeSheet, final WriteBlock writeBlock,
                                    final Map<String, Object> params, final Map<String, CellStyle> writeCellStyleCache){
        TagData tagData = writeBlock.getTagData();

        if(tagData instanceof ConstTagData){
            tagData.getReadRowData().stream().forEach(rowData -> {
                final Row writeRow = writeSheet.createRow(rowData.getRowNum());
                writeRow.setHeight(rowData.getHeight());
                writeRow.setHeightInPoints(rowData.getHeightInPoints());
                if(CollUtil.isEmpty(rowData.getCellDatas())){
                    return;
                }
                rowData.getCellDatas().forEach((readCellNum, cellData) -> writeCellData(writeWb, writeRow, rowData.getRowNum(), cellData, params, writeCellStyleCache));
            });
        }
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
