package com.sb.stu.npoi.common.bean.write.tag;

import cn.hutool.core.util.StrUtil;
import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import com.sb.stu.npoi.common.consts.SaxExcelConst;
import com.sb.stu.npoi.common.consts.TagEnum;
import com.sb.stu.npoi.common.util.ExprUtil;
import com.sb.stu.npoi.common.util.write.TagUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.util.Map;

/**
 * 编  号：
 * 名  称：IfTagData
 * 描  述：
 * 完成日期：2019/2/5 21:29
 * @author：felix.shao
 */
public class IfTagData extends TagData{

    @Override
    public String getRealExpr() {
        return null != value ?
                String.valueOf(value).replace(SaxExcelConst.TAG_KEY + TagEnum.IF_TAG.getKey(), "").trim()
                : "";
    }

    @Override
    public void writeTagData(Workbook writeWb, SXSSFSheet writeSheet, WriteSheetData writeSheetData,
                             Map<String, Object> params, Map<String, CellStyle> writeCellStyleCache) {
        TagUtil.writeTagData(writeWb, writeSheet, writeSheetData, readRowData, params, writeCellStyleCache);
    }

    @Override
    public boolean isExprTrue(Map<String, Object> params){
        String exprStr = getRealExpr();
        Object exprValue = ExprUtil.getExprStrValue(params, exprStr);
        boolean isFlag = null == exprValue || StrUtil.isEmpty(exprValue.toString()) ? false : true;
        return isFlag;
    };

}
