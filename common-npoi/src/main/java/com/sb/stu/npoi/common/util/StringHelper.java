package com.sb.stu.npoi.common.util;

import java.util.*;
import java.util.regex.*;

/**
 * 编  号：
 * 名  称：StringHelper
 * 描  述：
 * 完成日期：2019/01/31 01:47
 *
 * @author：felix.shao
 */
public class StringHelper {

    public static String renderString(String content, Map<String, Object> map){
        Set<Map.Entry<String, Object>> sets = map.entrySet();
        for(Map.Entry<String, Object> entry : sets) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(content);
            content = matcher.replaceAll(entry.getValue().toString());
        }
        return content;
    }

    public static void main(String[] args) {
        String content = "hello ${name}, 1 2 3 4 5 ${six} 7, again ${name}. ";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "java");
        map.put("six", "6");
        content = StringHelper.renderString(content, map);
        System.out.println(content);
    }

}
