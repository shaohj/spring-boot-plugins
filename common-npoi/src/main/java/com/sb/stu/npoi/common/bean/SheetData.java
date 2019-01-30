package com.sb.stu.npoi.common.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 编  号：
 * 名  称：SheetData
 * 描  述：
 * 完成日期：2019/01/28 23:46
 *
 * @author：felix.shao
 */
@Data
public class SheetData {

    private int sheetNum;

    private String sheetName;

    /** 列宽度 */
    private int[] cellWidths;

    private Map<String, RowData> rowDatas;

}
