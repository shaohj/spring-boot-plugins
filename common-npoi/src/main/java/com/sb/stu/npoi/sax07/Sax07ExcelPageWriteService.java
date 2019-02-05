package com.sb.stu.npoi.sax07;

import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import com.sb.stu.npoi.common.bean.write.tag.TagData;
import lombok.Data;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.util.Map;

/**
 * 编  号：
 * 名  称：Sax07ExcelPageWriteService
 * 描  述：实现大数据分页导出的service
 * 完成日期：2019/02/05 13:20
 *
 * @author：felix.shao
 */
@Data
public abstract class Sax07ExcelPageWriteService {

    protected TagData tagData;

    protected String exprVal;

    protected Workbook writeWb;

    protected SXSSFSheet writeSheet;

    protected WriteSheetData writeSheetData;

    protected Map<String, CellStyle> writeCellStyleCache;

    public void init(TagData tagData, Workbook writeWb,
                     SXSSFSheet writeSheet, WriteSheetData writeSheetData, Map<String, CellStyle> writeCellStyleCache){
        this.tagData = tagData;
        this.writeWb = writeWb;
        this.writeSheet = writeSheet;
        this.writeSheetData = writeSheetData;
        this.writeCellStyleCache = writeCellStyleCache;

    }
    /**
     * 分页查询出data，并调用tagData.writeTagData(writeWb, writeSheet, writeSheetData, pageParams, writeCellStyleCache);将分页的数据写入Excel
     */
    public abstract void pageWriteData();

}
