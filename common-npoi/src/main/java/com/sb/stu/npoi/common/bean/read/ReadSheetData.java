package com.sb.stu.npoi.common.bean.read;

import lombok.Data;

import java.util.Map;

/**
 * 编  号：
 * 名  称：ReadSheetData
 * 描  述：
 * 完成日期：2019/01/28 23:46
 *
 * @author：felix.shao
 */
@Data
public class ReadSheetData {

    private int sheetNum;

    private String sheetName;

    /** 列宽度 */
    private int[] cellWidths;

    private Map<String, RowData> rowDatas;

}
