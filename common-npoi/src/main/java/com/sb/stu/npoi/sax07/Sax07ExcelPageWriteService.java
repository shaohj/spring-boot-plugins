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
 * 描  述：
 * 完成日期：2019/02/05 13:20
 *
 * @author：felix.shao
 */
@Data
public abstract class Sax07ExcelPageWriteService {

    TagData tagData;
    Workbook writeWb;
    SXSSFSheet writeSheet;
    WriteSheetData writeSheetData;
    Map<String, CellStyle> writeCellStyleCache;

    public abstract void pageWriteData();

}
