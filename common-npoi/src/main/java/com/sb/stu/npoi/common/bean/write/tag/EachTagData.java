package com.sb.stu.npoi.common.bean.write.tag;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.sb.stu.npoi.common.bean.read.CellData;
import com.sb.stu.npoi.common.bean.write.WriteSheetData;
import com.sb.stu.npoi.common.consts.SaxExcelConst;
import com.sb.stu.npoi.common.consts.TagEnum;
import com.sb.stu.npoi.common.util.ExprUtil;
import com.sb.stu.npoi.common.util.write.TagUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 编  号：
 * 名  称：ConstTagData
 * 描  述：
 * 完成日期：2019/02/02 00:37
 *
 * @author：felix.shao
 */
@Data
@Slf4j
public class EachTagData extends TagData{

    private String expr;

    private String modelName;

    /** 只遍历指定的属性 */
    private String onkeys;

    @Override
    public String getRealExpr() {
        return null != value ?
                String.valueOf(value).replace(SaxExcelConst.TAG_KEY + TagEnum.EACH_TAG.getKey(), "").trim()
                : "";
    }

    @Override
    public void writeTagData(Workbook writeWb, SXSSFSheet writeSheet, WriteSheetData writeSheetData,
                             Map<String, Object> params, Map<String, CellStyle> writeCellStyleCache) {
        initExpr();
        initReadRowData(params);
        log.info("EachTagData.readRowData parse\n-->\n{}", JSON.toJSONString(readRowData));
        TagUtil.writeTagData(writeWb, writeSheet, writeSheetData, readRowData, params, writeCellStyleCache);
    }

    /**
     * each标签将根据数据动态初始化需要写入的数据
     *   只会读取第一个RowData的第一个列数据，然后根据数据以第一列复制多个列
     * @param params
     */
    private void initReadRowData(Map<String, Object> params){
        Map<String, CellData> cellDataMap = readRowData.stream().findFirst().get().getCellDatas();
        if (!CollUtil.isEmpty(cellDataMap)){
            CellData eachCellData = cellDataMap.get("0");

            Object iteratorObj = ExprUtil.getExprStrValue(params, expr);
            if(null == iteratorObj){
                return;
            }
            Iterator iterator;
            if(iteratorObj instanceof Map){
                iterator =  ExprUtil.getIterator(iteratorObj);
            } else {
                iterator =  ExprUtil.getIterator(ExprUtil.getBeanProperties(iteratorObj.getClass()));
            }

            int colNum = 0;
            while (iterator.hasNext()) {
                Object o = iterator.next();

                String property = "";
                if (o instanceof Field) {
                    property = ((Field) o).getName();
                } else if (o instanceof Map.Entry) {
                    property = ((Map.Entry) o).getKey().toString();
                } else if (o instanceof DynaProperty) {
                    property = ((DynaProperty) o).getName();
                } else {
                    property = o.toString();
                }

                property = SaxExcelConst.EXPR_START + modelName + "." + property + SaxExcelConst.EXPR_END;

                CellData colCellData = new CellData(colNum, property, eachCellData.getCellStyle(), eachCellData.getCellType());
                cellDataMap.put(String.valueOf(colNum ++), colCellData);
            }
        }
    }

    private void initExpr(){
        String realExpr = getRealExpr();
        if(StrUtil.isEmpty(realExpr)){
            return;
        }
        StringTokenizer st = new StringTokenizer(realExpr, " ");
        int pos = 0;
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            if (pos == 0) {
                expr = str;
            }
            if (pos == 1 && !"on".equals(str)) {
                onkeys = str;
            }
            if (pos == 2) {
                onkeys = str;
            }
            pos++;
        }

        modelName = expr.substring(SaxExcelConst.EXPR_START.length(), expr.length() - SaxExcelConst.EXPR_END.length());
    };

}
