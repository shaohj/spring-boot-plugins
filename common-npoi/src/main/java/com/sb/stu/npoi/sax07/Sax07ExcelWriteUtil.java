package com.sb.stu.npoi.sax07;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import com.sb.stu.npoi.common.bean.write.tag.TagData;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.*;

/**
 * 编  号：
 * 名  称：Sax07ExcelWriteUtil
 * 描  述：
 * 完成日期：2019/02/04 16:17
 *
 * @author：felix.shao
 */
public class Sax07ExcelWriteUtil {

    public static void writeSheetData(SXSSFWorkbook writeWb, Map<String, Object> params, List<WriteSheetData> writeSheetDatas){
        if(CollUtil.isEmpty(writeSheetDatas)){
            return;
        }

        writeSheetDatas.stream().forEach(writeSheetData -> {
            //创建sheet
            SXSSFSheet writeSheet =  writeWb.createSheet(writeSheetData.getSheetName());

            /** 设置列宽为模板文件的列宽 */
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
            writeSheetData.getWriteBlocks().forEach((readRowNum, writeBlock) -> {
                TagData tagData = writeBlock.getTagData();
                tagData.writeTagData(writeWb, writeSheet, writeSheetData, params, writeCellStyleCache);
            });
        });
    }

}
