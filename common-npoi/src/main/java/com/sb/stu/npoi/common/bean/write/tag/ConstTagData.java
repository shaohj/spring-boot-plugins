package com.sb.stu.npoi.common.bean.write.tag;

import com.sb.stu.npoi.common.bean.read.RowData;
import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import com.sb.stu.npoi.common.util.write.TagUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 编  号：
 * 名  称：ConstTagData
 * 描  述：
 * 完成日期：2019/02/02 00:37
 *
 * @author：felix.shao
 */
@Data
@NoArgsConstructor
public class ConstTagData extends TagData{

    @Override
    public String getRealExpr() {
        return String.valueOf(value);
    }

    protected List<RowData> readRowData;

    public ConstTagData(List<RowData> readRowData) {
        this.readRowData = readRowData;
    }

    public void addRowData(RowData rowData){
        if(null == readRowData){
            //一般标签内的excel行数不会太多，设置为4吧
            readRowData = new ArrayList<>(4);
        }
        readRowData.add(rowData);
    }

    @Override
    public void writeTagData(Workbook writeWb, SXSSFSheet writeSheet, WriteSheetData writeSheetData,
                             Map<String, Object> params, Map<String, CellStyle> writeCellStyleCache) {
        TagUtil.writeTagData(writeWb, writeSheet, writeSheetData, readRowData, params, writeCellStyleCache);
    }

}
