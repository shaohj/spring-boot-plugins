package com.sb.stu.npoi.sax07;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 编  号：
 * 名  称：Sax07ExcelUtilTest
 * 描  述：
 * 完成日期：2019/01/29 00:27
 *
 * @author：felix.shao
 */
public class Sax07ExcelUtilTest {

    public static final String exportPath = "E:\\temp\\export\\";

    @Test
    public void exportTest(){
        String tempPath = "xlsx/";
        String tempFilePath = tempPath + "demo_each.xlsx";

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(exportPath + "demo_each_exp2.xlsx");
            Sax07ExcelUtil.export(tempFilePath, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(fos);
        }
    }


}
