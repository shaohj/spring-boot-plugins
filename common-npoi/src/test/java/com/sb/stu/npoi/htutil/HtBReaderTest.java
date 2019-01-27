package com.sb.stu.npoi.htutil;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.Excel07SaxReader;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 编  号：
 * 名  称：HtBReaderTest
 * 描  述：
 * 完成日期：2019/01/27 17:21
 *
 * @author：felix.shao
 */
@Slf4j
public class HtBReaderTest {

    /**
     * 复用刚才导出时的excel map文件
     */
    @Test
    public void htbMapTest() {
        long t1 = System.currentTimeMillis();

        ExcelReader reader;

        /**
         * -ea -Xmx8m -Xms8m -Xmn8m -Xss16m  40W数据量<临界值<100W数据量,一次性读出时内存会溢出
         */
        //通过sheet编号获取
//        reader = ExcelUtil.getReader(FileUtil.file("E:\\temp\\export\\htbMapByOneTest.xlsx"), 0);
        reader = ExcelUtil.getReader(FileUtil.file("E:\\temp\\export\\htbMapByPageTest.xlsx"), 0);

        List<Map<String,Object>> readAll = reader.readAll();

        log.info("读取{}条数据,耗费时间为{}毫秒", readAll.size(), System.currentTimeMillis() - t1);
    }

    @Test
    public void htb07SaxMapTest() {
        long t1 = System.currentTimeMillis();

        /**
         * -ea -Xmx8m -Xms8m -Xmn8m -Xss16m  40W数据量<临界值<100W数据量,一次性读出时内存会溢出，大数据读出不会报错
         */

        Excel07SaxReader reader = new Excel07SaxReader(createRowHandler());
        reader.read("E:\\temp\\export\\htbMapByPageTest.xlsx", -1);

        log.info("读取{}条数据,耗费时间为{}毫秒", num, System.currentTimeMillis() - t1);
    }

    private static int num = 0;
    private RowHandler createRowHandler() {
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, int rowIndex, List<Object> rowlist) {
                num ++;
                if(num%10000 == 0) {
                    log.info("已经读取了{}条数据", num);
                }
//                log.info("[{}] [{}] {}", sheetIndex, rowIndex, rowlist);
            }
        };
    }


}
