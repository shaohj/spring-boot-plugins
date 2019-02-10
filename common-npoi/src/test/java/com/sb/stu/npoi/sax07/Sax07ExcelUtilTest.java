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
    public void exportEachTest(){
        long t1 = System.currentTimeMillis();

        String tempPath = "xlsx/";
        String tempFilePath = tempPath + "each_temp.xlsx";

        FileOutputStream fos = null;

        Map<String, Object> map = new HashMap<>();
        map.put("printDate", "2019-01-31");

        ModelTest model = new ModelTest("aaa1", "bbb", 123.234);
        model.setYear("1992");
        map.put("model", model);

        try {
            fos = new FileOutputStream(exportPath + "each_data.xlsx");
            Sax07ExcelUtil.export(tempFilePath, map, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(fos);
        }
        log.info("导出1条数据,耗费时间为{}毫秒", System.currentTimeMillis() - t1);
    }

    @Test
    public void exportEachMapTest(){
        long t1 = System.currentTimeMillis();

        String tempPath = "xlsx/";
        String tempFilePath = tempPath + "each_temp.xlsx";

        FileOutputStream fos = null;

        Map<String, Object> map = new HashMap<>();
        map.put("printDate", "2019-01-31");

        Map model = new LinkedHashMap();
        model.put("uname", "zhangsan");
        model.put("realname", "张三");
        model.put("age", 19);

        map.put("model", model);

        try {
            fos = new FileOutputStream(exportPath + "each_data.xlsx");
            Sax07ExcelUtil.export(tempFilePath, map, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(fos);
        }
        log.info("导出1条数据,耗费时间为{}毫秒", System.currentTimeMillis() - t1);
    }

    @Test
    public void exportForeachTest(){
        log.info("缓存导出的xlsx临时文件目录为:{}", System.getProperty("java.io.tmpdir"));

        long t1 = System.currentTimeMillis();
        int num = 4;

        String tempPath = "xlsx/";
        String tempFilePath = tempPath + "foreach_temp.xlsx";

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
            fos = new FileOutputStream(exportPath + "foreach_data.xlsx");
            Sax07ExcelUtil.export(tempFilePath, map, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(fos);
        }
        log.info("导出{}条数据,耗费时间为{}毫秒", num, System.currentTimeMillis() - t1);
    }

    @Test
    public void exportPageForeachTest(){
        log.info("缓存导出的xlsx临时文件目录为:{}", System.getProperty("java.io.tmpdir"));

        long t1 = System.currentTimeMillis();
        int num = 105;
        int pageSize = 10;
        int totalPageNum = num % pageSize == 0 ? num/pageSize : num/pageSize + 1;

        String tempPath = "xlsx/";
        String tempFilePath = tempPath + "pageforeach_temp.xlsx";

        FileOutputStream fos = null;

        Map<String, Object> map = new HashMap<>();
        map.put("printDate", "2019-01-31");

        ModelTest model = new ModelTest("aaa1", "bbb", 123.234);
        model.setYear("1992");
        map.put("model", model);

        try {
            Sax07ExcelPageWriteService sax07ExcelPageWriteService = new Sax07ExcelPageWriteService(){
                @Override
                public void pageWriteData() {
                    for (int i = 0; i <totalPageNum; i++) {
                        Map<String, Object> pageParams = new HashMap<>();
                        List details = new ArrayList(pageSize);
                        for (int j = 0; j <pageSize && pageSize * (i+1) + j < num; j++) {
                            details.add(new ModelTest("user" + j, "world", 144.342));
                        }
                        pageParams.put("list", details);
                        tagData.writeTagData(writeWb, writeSheet, writeSheetData, pageParams, writeCellStyleCache);
                    }
                }
            };
            //设置sax07ExcelPageWriteService对应的表达式
            sax07ExcelPageWriteService.setExprVal("#pageforeach detail in ${list}");

            fos = new FileOutputStream(exportPath + "pageforeach_data.xlsx");
            Sax07ExcelUtil.export(tempFilePath, map, fos, sax07ExcelPageWriteService);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(fos);
        }
        log.info("导出{}条数据,耗费时间为{}毫秒", num, System.currentTimeMillis() - t1);
    }

}
