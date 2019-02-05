package com.sb.stu.npoi.sax07;

import cn.hutool.core.io.IoUtil;
import com.sb.stu.npoi.common.entity.ModelTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.*;

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
        long t1 = System.currentTimeMillis();
        int num = 10000;

        log.info("缓存导出的xlsx临时文件目录为:{}", System.getProperty("java.io.tmpdir"));
        String tempPath = "xlsx/";
        String tempFilePath = tempPath + "demo_each.xlsx";

        FileOutputStream fos = null;

        Map<String, Object> map = new HashMap<>();
        map.put("printDate", "2019-01-31");

        ModelTest model = new ModelTest("aaa1", "bbb", 123.234);
        model.setYear("1992");
        map.put("model", model);

        List details = new ArrayList();
        //i条数据导出测试
        for(int i = 0; i< num; i++){
            details.add(new ModelTest("user" + i, "world", 144.342));
        }
        map.put("list", details);

        try {
            fos = new FileOutputStream(exportPath + "demo_each_exp2.xlsx");
            Sax07ExcelUtil.export(tempFilePath, map, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(fos);
        }
        log.info("导出{}条数据,耗费时间为{}毫秒", num, System.currentTimeMillis() - t1);
    }


}
