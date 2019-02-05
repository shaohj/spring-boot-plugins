package com.sb.stu.npoi.common.bean.write;

import lombok.Data;

import java.util.Map;

/**
 * 编  号：
 * 名  称：WriteSheetData
 * 描  述：
 * 完成日期：2019/02/02 01:00
 *
 * @author：felix.shao
 */
@Data
public class WriteSheetData {

    private int sheetNum;

    private String sheetName;

    /** 当前写入行号 */
    private int curWriteRowNum;

    /** 当前写入列号 */
    private int curWriteColNum;

    /** 列宽度 */
    private int[] cellWidths;

    private Map<String, WriteBlock> writeBlocks;

    public int getCurWriteRowNumAndIncrement(){
        return curWriteRowNum++;
    }

}
