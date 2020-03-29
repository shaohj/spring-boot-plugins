package com.sprboot.plugin.freemarkerex.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 编  号：
 * 名  称：Java8DateUtils
 * 描  述：
 * 完成日期：2020/3/29 20:17
 * @author：felix.shao
 */
public class Java8DateUtils {

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String getYyyyMmDDHhMmSs(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS);
        return dtf.format(LocalDateTime.now());
    }

    public static void main(String[] args) {
        System.out.println(getYyyyMmDDHhMmSs());
    }
}
