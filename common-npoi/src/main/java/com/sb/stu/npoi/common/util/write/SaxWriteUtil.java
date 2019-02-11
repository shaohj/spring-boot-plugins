package com.sb.stu.npoi.common.util.write;

import cn.hutool.core.collection.CollUtil;
import com.sb.stu.npoi.common.bean.read.CellData;
import com.sb.stu.npoi.common.bean.read.ReadSheetData;
import com.sb.stu.npoi.common.bean.read.RowData;
import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import com.sb.stu.npoi.common.bean.write.tag.*;
import com.sb.stu.npoi.common.consts.TagEnum;
import com.sb.stu.npoi.common.util.CalculationUtil;
import com.sb.stu.npoi.common.util.ExcelCommonUtil;
import com.sb.stu.npoi.common.util.ExprUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.*;

/**
 * 编  号：
 * 名  称：SaxWriteUtil
 * 描  述：
 * 完成日期：2019/02/02 00:40
 *
 * @author：felix.shao
 */
public class SaxWriteUtil {

    /**
     * 将读取的readSheetDatas转为需要写入的WriteSheetDatas
     * @param readSheetDatas
     * @return
     */
    public static List<WriteSheetData> parseSheetData(List<ReadSheetData> readSheetDatas){
        if(CollUtil.isEmpty(readSheetDatas)){
            return new ArrayList<>(0);
        }
        final List<WriteSheetData> writeSheetDatas = new ArrayList<>(readSheetDatas.size());
        readSheetDatas.stream().forEach(readSheetData -> writeSheetDatas.add(parseSheetData(readSheetData)));
        return writeSheetDatas;
    }

    /**
     * 将读取的readSheetData转为需要写入的WriteSheetData
     * @param readSheetData
     * @return
     */
    private static WriteSheetData parseSheetData(ReadSheetData readSheetData){
        WriteSheetData writeSheetData = new WriteSheetData();
        writeSheetData.setSheetNum(readSheetData.getSheetNum());
        writeSheetData.setSheetName(readSheetData.getSheetName());
        writeSheetData.setCellWidths(readSheetData.getCellWidths());
        writeSheetData.setWriteBlocks(parseRowData(readSheetData.getRowDatas()));

        return writeSheetData;
    }

    /**
     * 将读取的rowDatas判断标签转为写入时的块
     * @param rowDatas
     * @return
     */
    public static Map<String, TagData> parseRowData(Map<String, RowData> rowDatas){
        if(CollUtil.isEmpty(rowDatas)){
            return new HashMap<>(0);
        }

        final Map<String, TagData> writeBlockMap = new LinkedHashMap<>(CalculationUtil.calMapCapacity(rowDatas.size()));

        geneTreeWriteBlock(0,rowDatas.size() - 1, rowDatas, null, writeBlockMap);

        return writeBlockMap;
    }

    /**
     * 递归循环遍历读取的RowData，将RowData合并为一个一个的写入块
     *   如第m行是#foreach，第n行是对应的#end，那么第m~n行的代码块合并为一个FOREACH_TAG块
     * @param rowNumStart rowNum 块的开始行
     * @param rowNumEnd rowNum 块的结束行
     * @param rowDatas 原始的待写的row数据
     * @param rootTagData 父tagData
     * @param writeBlockMap 转换后的待写的block数据
     * @return
     */
    public static void geneTreeWriteBlock(int rowNumStart, int rowNumEnd, Map<String, RowData> rowDatas, TagData rootTagData, Map<String, TagData> writeBlockMap){
        if(rowNumStart < 0 || rowNumStart > rowNumEnd || rowNumEnd >= rowDatas.size()){
            return ;
        }
        int writeBlockNum = 0;

        int curRowNum = rowNumStart;
        while(curRowNum < rowNumEnd && rowNumEnd < rowDatas.size()){
            RowData rowData = rowDatas.get(String.valueOf(curRowNum));
            TagData tagData = null;
            TagEnum tagEnum = TagUtil.getTagEnum(rowData);

            int curRowEndNum = -1;
            switch (tagEnum){
                case IF_TAG:
                    tagData = new IfTagData();
                    tagData.setValue(getFirstCellValueStr(rowData));

                    curRowEndNum = TagUtil.getTagEndNum(curRowNum + 1, rowNumEnd, rowDatas);
                    geneTreeWriteBlock(curRowNum + 1, curRowEndNum, rowDatas, tagData, writeBlockMap);
                    curRowNum = curRowEndNum;
                    break;
                case FOREACH_TAG:
                    tagData = new ForeachTagData();
                    tagData.setValue(getFirstCellValueStr(rowData));
                    curRowEndNum = TagUtil.getTagEndNum(curRowNum + 1, rowNumEnd, rowDatas);
                    geneTreeWriteBlock(curRowNum + 1, curRowEndNum, rowDatas, tagData, writeBlockMap);
                    curRowNum = curRowEndNum;
                    break;
                case BIGFOREACH_TAG:
                    tagData = new PageForeachTagData();
                    tagData.setValue(getFirstCellValueStr(rowData));
                    curRowEndNum = TagUtil.getTagEndNum(curRowNum + 1, rowNumEnd, rowDatas);
                    geneTreeWriteBlock(curRowNum + 1, curRowEndNum, rowDatas, tagData, writeBlockMap);
                    curRowNum = curRowEndNum;
                    break;
                case EACH_TAG:
                    tagData = new EachTagData(rowData);
                    tagData.setValue(getFirstCellValueStr(rowData)); break;
                case CONST_TAG:
                default:
                    tagData = new ConstTagData(Arrays.asList(rowData)); break;
            }

            if(null == rootTagData){
                writeBlockMap.put(String.valueOf(writeBlockNum), tagData);
            } else {
                rootTagData.getChildTagDatas().add(tagData);
            }

            writeBlockNum ++;
            curRowNum ++;
        }
    }

    public static String getFirstCellValueStr(RowData rowData){
        String expr = null == rowData || CollUtil.isEmpty(rowData.getCellDatas()) || null == rowData.getCellDatas().get("0") ?
                null : String.valueOf(rowData.getCellDatas().get("0").getValue());
        return expr;
    };

    /**
     * 写入Excel Cell数据和样式
     *   foreach和bigforeach标签在写入样式时，使用了缓存的样式，避免创建很多相同的样式导致excel文件过大
     * @param writeWb
     * @param writeRow
     * @param readRowNum
     * @param cellData
     * @param params java动态参数
     * @param writeCellStyleCache 样式缓存
     */
    public static void writeCellData(Workbook writeWb, Row writeRow, int readRowNum, CellData cellData,
                                     Map<String, Object> params, Map<String, CellStyle> writeCellStyleCache){
        Cell writeCell = writeRow.createCell(cellData.getColNum());

        String cellStyleKey = readRowNum + "_" + cellData.getColNum();
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

        Object parseValue = ExprUtil.parseTempStr(params, cellData.getValue());
        ExcelCommonUtil.setCellValue(writeCell, parseValue);
    }

}
