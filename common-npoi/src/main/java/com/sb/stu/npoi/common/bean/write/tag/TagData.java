package com.sb.stu.npoi.common.bean.write.tag;

import com.sb.stu.npoi.common.bean.RowData;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 编  号：
 * 名  称：TagData
 * 描  述：
 * 完成日期：2019/02/02 00:33
 *
 * @author：felix.shao
 */
@Data
public abstract class TagData {

    protected Object value;

    protected List<RowData> readRowData;

    public void addRowData(RowData rowData){
        if(null == readRowData){
            readRowData = new ArrayList<>(2);
        }
        readRowData.add(rowData);
    }

    public abstract String getRealExpr();

}
