package com.sb.stu.npoi.common.bean;

import lombok.Data;

import java.util.List;

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

    private List<RowData> rowDatas;

}
