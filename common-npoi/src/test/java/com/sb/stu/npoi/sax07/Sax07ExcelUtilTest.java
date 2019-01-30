package com.sb.stu.npoi.sax07;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 编  号：
 * 名  称：Sax07ExcelUtilTest
 * 描  述：
 * 完成日期：2019/01/29 00:27
 *
 * @author：felix.shao
 */
@Slf4j
public class Sax07ExcelUtilTest {

    public static final String exportPath = "E:\\temp\\export\\";

    @Test
    public void exportTest(){
        log.info("缓存导出的xlsx临时文件目录为:{}", System.getProperty("java.io.tmpdir"));
        String tempPath = "xlsx/";
        String tempFilePath = tempPath + "demo_each.xlsx";

        FileOutputStream fos = null;

        Map<String, Object> map = new HashMap<>();
        map.put("printDate", "2019-01-31");
        try {
            fos = new FileOutputStream(exportPath + "demo_each_exp2.xlsx");
            Sax07ExcelUtil.export(tempFilePath, map, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(fos);
        }
    }


}
