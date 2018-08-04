package com.poi.template.excel.parse.cache.poiutils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class CacheSheetUtils {

    /**
     * 获取Sheet最大的数据列数
     * @param sheet :
     * @return int  最大列数为3则返回3
     * @author SHJ
     * @since 2018/7/8 13:44
     */
    public static int getMaxCellNum(Sheet sheet){
        int rows = sheet.getPhysicalNumberOfRows();
        int maxCellNum = 0;

        for(int i=0; i< rows; i++){
            Row tRow = sheet.getRow(i);
            if(null != tRow){
                int tempNum = tRow.getLastCellNum();
                maxCellNum = tempNum > maxCellNum ? tempNum : maxCellNum;
            }
        }
        return maxCellNum;
    }

    /**
     * 获取sheet的每个列宽
     * @param sheet :
     * @param maxCellNum :
     * @return int[]
     * @author SHJ
     * @since 2018/7/8 13:45
     */
    public static int[] getCellWidths(Sheet sheet, int maxCellNum){
        int[] cellWidths = new int[maxCellNum];

        for (int i = 0; i < maxCellNum; i++) {
            cellWidths[i] = sheet.getColumnWidth(i);
        }

        return cellWidths;
    }

}
