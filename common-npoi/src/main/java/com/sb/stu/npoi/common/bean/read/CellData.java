package com.sb.stu.npoi.common.bean.read;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;

/**
 * 编  号：
 * 名  称：CellData
 * 描  述：
 * 完成日期：2019/01/28 23:41
 *
 * @author：felix.shao
 */
@Data
public class CellData {

    /** 列号 */
    private int colNum;

    /** 值 */
    private Object value;

    /** 样式 */
    @JSONField(serialize = false)
    private CellStyle cellStyle;

    @JSONField(serialize = false)
    private CellType cellType;

}
