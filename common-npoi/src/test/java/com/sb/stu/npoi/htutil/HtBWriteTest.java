package com.sb.stu.npoi.htutil;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;

/**
 * 编  号：
 * 名  称：HtBWriteTest
 * 描  述：
 * 完成日期：2019/01/27 17:11
 *
 * @author：felix.shao
 */
@Slf4j
public class HtBWriteTest {

    @Test
    public void htbListTest() {
        long t1 = System.currentTimeMillis();
        int num = 10000;

        List<List<?>> rows = new ArrayList<>(num);

        List<?> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd", DateUtil.date(), 3.22676575765);
        for (int i = 0; i <num ; i++) {
            rows.add(row1);
        }

        BigExcelWriter writer= ExcelUtil.getBigWriter("E:\\temp\\export\\htbListTest.xlsx");

        // 一次性写出内容，使用默认样式
        writer.write(rows);
    // 关闭writer，释放内存
        writer.close();

        log.info("导出{}条数据,耗费时间为{}毫秒", num, System.currentTimeMillis() - t1);
    }

    @Test
    public void htbMapByOneTest() {
        long t1 = System.currentTimeMillis();
        /**
         * -ea -Xmx8m -Xms8m -Xmn8m -Xss16m  40W数据量<临界值<100W数据量,一次性写出时内存会溢出
         */
        int num = 1;

        List<Map<String, Object>> rows = new ArrayList<>(num);

        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        for (int i = 0; i <num ; i++) {
            rows.add(row1);
        }

        BigExcelWriter writer= ExcelUtil.getBigWriter("E:\\temp\\export\\htbMapByOneTest.xlsx");

        // 一次性写出内容，使用默认样式
        writer.passCurrentRow();
        writer.write(rows);
        // 关闭writer，释放内存
        writer.close();

        log.info("导出{}条数据,耗费时间为{}毫秒", num, System.currentTimeMillis() - t1);
    }

    @Test
    public void htbMapByPageTest() {
        long t1 = System.currentTimeMillis();
        /**
         * -ea -Xmx8m -Xms8m -Xmn8m -Xss16m  40W数据量<临界值<100W数据量,一次性写出时内存会溢出，分页写出不会报错
         */
        int num = 1000000;
        int pageSize = 10000;
        int totalPageNum = num/pageSize;
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        BigExcelWriter writer= ExcelUtil.getBigWriter("E:\\temp\\export\\htbMapByPageTest.xlsx");

        // 分页写出内容，使用默认样式
        log.info("分页写出内容，总页数={},每页数={},总数据量={}", totalPageNum, pageSize, num);
        for (int i = 0; i <totalPageNum; i++) {
            List<Map<String, Object>> rows = new ArrayList<>(pageSize);
            for (int j = 0; j <pageSize ; j++) {
                rows.add(row1);
            }
            writer.write(rows);
        }
        // 关闭writer，释放内存
        writer.close();
        log.info("导出{}条数据,耗费时间为{}毫秒", num, System.currentTimeMillis() - t1);
    }

}
