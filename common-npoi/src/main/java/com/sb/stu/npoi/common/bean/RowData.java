package com.sb.stu.npoi.common.bean;

import lombok.Data;

import java.util.List;

/**
 * 编  号：
 * 名  称：RowData
 * 描  述：
 * 完成日期：2019/01/28 23:41
 *
 * @author：felix.shao
 */
@Data
public class RowData {

    /** 行号 */
    private int rowNum;

    private List<CellData> cellDatas;

}
