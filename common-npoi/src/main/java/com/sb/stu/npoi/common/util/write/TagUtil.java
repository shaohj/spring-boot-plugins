package com.sb.stu.npoi.common.util.write;

import cn.hutool.core.collection.CollUtil;
import com.sb.stu.npoi.common.bean.read.RowData;
import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import com.sb.stu.npoi.common.consts.TagEnum;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.util.List;
import java.util.Map;

/**
 * 编  号：
 * 名  称：TagUtil
 * 描  述：
 * 完成日期：2019/02/05 12:49
 *
 * @author：felix.shao
 */
public class TagUtil {

    public static TagEnum getTagEnum(RowData rowData){
        String expr = SaxWriteUtil.getFirstCellValueStr(rowData);
        return TagEnum.getTagEnum(expr);
    };

    public static int getTagEndNum(int rowNumStart, int rowNumEnd, Map<String, RowData> rowDatas){
        if(rowNumStart < 0 || rowNumStart > rowNumEnd || rowNumEnd >= rowDatas.size()){
            return -1;
        }

        int curRowNum = rowNumStart;

        // tag存在嵌套
        int tagNum = 0;
        while(curRowNum <= rowNumEnd && rowNumEnd < rowDatas.size()){
            RowData rowData = rowDatas.get(String.valueOf(curRowNum));
            TagEnum tagEnum = getTagEnum(rowData);
            if(tagEnum.isHasEndTag()){
                // 嵌套标签且有结束标签
                tagNum ++;
            } else {
                boolean isEnd = isEndTag(rowData);
                if (isEnd) {
                    if(0 == tagNum){
                        return curRowNum;
                    } else {
                        tagNum --;
                    }
                }
            }
            curRowNum ++;
        }
        return -1;
    }

    public static void writeTagData(Workbook writeWb, SXSSFSheet writeSheet, WriteSheetData writeSheetData,
                                    List<RowData> readRowData, Map<String, Object> params, Map<String, CellStyle> writeCellStyleCache){
        readRowData.stream().forEach(rowData -> {
            final Row writeRow = writeSheet.createRow(writeSheetData.getCurWriteRowNumAndIncrement());
            writeRow.setHeight(rowData.getHeight());
            writeRow.setHeightInPoints(rowData.getHeightInPoints());
            if(CollUtil.isEmpty(rowData.getCellDatas())){
                return;
            }
            rowData.getCellDatas().forEach((readCellNum, cellData) -> SaxWriteUtil.writeCellData(writeWb, writeRow, rowData.getRowNum(), cellData, params, writeCellStyleCache));
        });
    }

    public static boolean isEndTag(RowData rowData){
        String expr = SaxWriteUtil.getFirstCellValueStr(rowData);
        return TagEnum.isEndTagNum(expr);
    }

}
