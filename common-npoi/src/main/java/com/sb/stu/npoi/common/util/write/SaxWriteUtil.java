package com.sb.stu.npoi.common.util.write;

import cn.hutool.core.collection.CollUtil;
import com.sb.stu.npoi.common.bean.CellData;
import com.sb.stu.npoi.common.bean.ReadSheetData;
import com.sb.stu.npoi.common.bean.RowData;
import com.sb.stu.npoi.common.bean.write.WriteBlock;
import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import com.sb.stu.npoi.common.bean.write.tag.ConstTagData;
import com.sb.stu.npoi.common.bean.write.tag.ForeachTagData;
import com.sb.stu.npoi.common.bean.write.tag.TagData;
import com.sb.stu.npoi.common.consts.SaxExcelConst;
import com.sb.stu.npoi.common.consts.TagEnum;
import com.sb.stu.npoi.common.util.CalculationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public static Map<String, WriteBlock> parseRowData(Map<String, RowData> rowDatas){
        if(CollUtil.isEmpty(rowDatas)){
            return new HashMap<>(0);
        }
        final Map<String, WriteBlock> writeBlockMap = new HashMap<>(CalculationUtil.calMapCapacity(rowDatas.size()));

        int startRowNum = 0;
        int writeBlockNum = 0;
        while(startRowNum < rowDatas.size()){
            WriteBlock writeBlock = geneTreeWriteBlock(startRowNum, rowDatas);
            if(null != writeBlock){
                writeBlockMap.put(String.valueOf(writeBlockNum), writeBlock);
                writeBlockNum ++;
            } else {
                break;
            }
        }

        return writeBlockMap;
    }

    /**
     * 递归循环遍历读取的RowData，将RowData合并为一个一个的写入块
     *   如第m行是#foreach，第n行是对应的#end，那么第m~n行的代码块合并为一个FOREACH_TAG块
     * @param rowNum rowNum和rowDatas的key是对应的关系
     * @param rowDatas
     * @return
     */
    public static WriteBlock geneTreeWriteBlock(int rowNum, Map<String, RowData> rowDatas){
        if(rowNum >= rowDatas.size()){
            return null;
        }
//        WriteBlock writeBlock = new WriteBlock();
//
//        RowData rowData = rowDatas.get(rowNum);
//        TagData tagData = null;
//        TagEnum tagEnum = getTagEnum(rowData);
//
//        switch (tagEnum){
//            case FOREACH_TAG:
//                tagData = new ForeachTagData();
//
//                tagData.addRowData(rowData); break;
//            case CONST_TAG:
//            default:
//                tagData = new ConstTagData();
//                tagData.addRowData(rowData); break;
//        }
//
//            writeBlock.setTagData(tagData);
//            writeBlockMap.put(rowNum, writeBlock);
        return null;
    }

    public static TagEnum getTagEnum(RowData rowData){
        String expr = CollUtil.isEmpty(rowData.getCellDatas()) || null == rowData.getCellDatas().get("0") ?
                null : String.valueOf(rowData.getCellDatas().get("0").getValue());
        return TagEnum.getTagEnum(expr);
    };

}
