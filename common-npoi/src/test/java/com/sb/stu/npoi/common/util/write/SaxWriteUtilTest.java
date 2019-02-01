package com.sb.stu.npoi.common.util.write;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sb.stu.npoi.common.bean.ReadSheetData;
import com.sb.stu.npoi.common.bean.RowData;
import com.sb.stu.npoi.common.bean.write.WriteBlock;
import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 编  号：
 * 名  称：SaxWriteUtilTest
 * 描  述：
 * 完成日期：2019/02/02 00:42
 *
 * @author：felix.shao
 */
@Slf4j
public class SaxWriteUtilTest {

    @Test
    public void parseSheetDataTest(){
        String readReadSheetDataStr = "[{\"cellWidths\":[12714,13937,5831],\"rowDatas\":{\"0\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"生成日期${printDate}\"},\"1\":{\"colNum\":1,\"value\":\"\"}},\"height\":684,\"heightInPoints\":34.2,\"rowNum\":0},\"1\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"用户姓名${model.name}\"},\"1\":{\"colNum\":1,\"value\":\"\"}},\"height\":564,\"heightInPoints\":28.2,\"rowNum\":1},\"2\":{\"cellDatas\":{},\"height\":744,\"heightInPoints\":37.2,\"rowNum\":2},\"3\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"姓名\"},\"1\":{\"colNum\":1,\"value\":\"工资\"},\"2\":{\"colNum\":2,\"value\":\"代号\"},\"3\":{\"colNum\":3,\"value\":\"\"}},\"height\":396,\"heightInPoints\":19.8,\"rowNum\":3},\"4\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"#foreach detail in ${list}\"},\"1\":{\"colNum\":1,\"value\":\"\"}},\"height\":684,\"heightInPoints\":34.2,\"rowNum\":4},\"5\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"${detail.name}\"},\"1\":{\"colNum\":1,\"value\":\"${detail.qty}\"},\"2\":{\"colNum\":2,\"value\":\"${detail.user}\"},\"3\":{\"colNum\":3,\"value\":\"\"}},\"height\":660,\"heightInPoints\":33.0,\"rowNum\":5},\"6\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"#end\"},\"1\":{\"colNum\":1,\"value\":\"\"}},\"height\":288,\"heightInPoints\":14.4,\"rowNum\":6},\"7\":{\"cellDatas\":{},\"height\":0,\"heightInPoints\":0.0,\"rowNum\":7},\"8\":{\"cellDatas\":{\"1\":{\"colNum\":1,\"value\":\"detail开始行：${detailStartRowNo}\"},\"2\":{\"colNum\":2,\"value\":\"\"}},\"height\":288,\"heightInPoints\":14.4,\"rowNum\":8}},\"sheetName\":\"Sheet1\",\"sheetNum\":0},{\"cellWidths\":[],\"rowDatas\":{\"0\":{\"cellDatas\":{},\"height\":0,\"heightInPoints\":0.0,\"rowNum\":0}},\"sheetName\":\"Sheet2\",\"sheetNum\":1},{\"cellWidths\":[],\"rowDatas\":{\"0\":{\"cellDatas\":{},\"height\":0,\"heightInPoints\":0.0,\"rowNum\":0}},\"sheetName\":\"Sheet3\",\"sheetNum\":2}]";
        List<ReadSheetData> readReadSheetData = JSONArray.parseArray(readReadSheetDataStr, ReadSheetData.class);

        List<WriteSheetData> writeSheetDatas = SaxWriteUtil.parseSheetData(readReadSheetData);
        log.info("writeSheetDatas.result=\n-->\n{}", JSON.toJSONString(writeSheetDatas));
    }

    @Test
    public void parseRowDataTest(){
        String readReadSheetDataStr = "[{\"cellWidths\":[12714,13937,5831],\"rowDatas\":{\"0\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"生成日期${printDate}\"},\"1\":{\"colNum\":1,\"value\":\"\"}},\"height\":684,\"heightInPoints\":34.2,\"rowNum\":0},\"1\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"用户姓名${model.name}\"},\"1\":{\"colNum\":1,\"value\":\"\"}},\"height\":564,\"heightInPoints\":28.2,\"rowNum\":1},\"2\":{\"cellDatas\":{},\"height\":744,\"heightInPoints\":37.2,\"rowNum\":2},\"3\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"姓名\"},\"1\":{\"colNum\":1,\"value\":\"工资\"},\"2\":{\"colNum\":2,\"value\":\"代号\"},\"3\":{\"colNum\":3,\"value\":\"\"}},\"height\":396,\"heightInPoints\":19.8,\"rowNum\":3},\"4\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"#foreach detail in ${list}\"},\"1\":{\"colNum\":1,\"value\":\"\"}},\"height\":684,\"heightInPoints\":34.2,\"rowNum\":4},\"5\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"${detail.name}\"},\"1\":{\"colNum\":1,\"value\":\"${detail.qty}\"},\"2\":{\"colNum\":2,\"value\":\"${detail.user}\"},\"3\":{\"colNum\":3,\"value\":\"\"}},\"height\":660,\"heightInPoints\":33.0,\"rowNum\":5},\"6\":{\"cellDatas\":{\"0\":{\"colNum\":0,\"value\":\"#end\"},\"1\":{\"colNum\":1,\"value\":\"\"}},\"height\":288,\"heightInPoints\":14.4,\"rowNum\":6},\"7\":{\"cellDatas\":{},\"height\":0,\"heightInPoints\":0.0,\"rowNum\":7},\"8\":{\"cellDatas\":{\"1\":{\"colNum\":1,\"value\":\"detail开始行：${detailStartRowNo}\"},\"2\":{\"colNum\":2,\"value\":\"\"}},\"height\":288,\"heightInPoints\":14.4,\"rowNum\":8}},\"sheetName\":\"Sheet1\",\"sheetNum\":0},{\"cellWidths\":[],\"rowDatas\":{\"0\":{\"cellDatas\":{},\"height\":0,\"heightInPoints\":0.0,\"rowNum\":0}},\"sheetName\":\"Sheet2\",\"sheetNum\":1},{\"cellWidths\":[],\"rowDatas\":{\"0\":{\"cellDatas\":{},\"height\":0,\"heightInPoints\":0.0,\"rowNum\":0}},\"sheetName\":\"Sheet3\",\"sheetNum\":2}]";
        List<ReadSheetData> readReadSheetData = JSONArray.parseArray(readReadSheetDataStr, ReadSheetData.class);

        Map<String, RowData> rowDatas = readReadSheetData.get(0).getRowDatas();
        Map<String, WriteBlock> writeBlocks = SaxWriteUtil.parseRowData(rowDatas);
        log.info("writeBlocks.result=\n-->\n{}", JSON.toJSONString(writeBlocks));
    }
}
