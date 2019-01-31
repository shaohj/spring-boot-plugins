package com.sb.stu.npoi.common.util;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 编  号：
 * 名  称：ExprUtilTest
 * 描  述：
 * 完成日期：2019/02/01 01:28
 *
 * @author：felix.shao
 */
public class ExprUtilTest {

    @Test
    public void getPropertyBySimpleTest(){
        Map<String, Object> params = new HashMap<>();

        params.put("printDate", LocalDateTime.now().toString().replace("T", " "));

        Map<String, Object> model = new HashMap<>();
        model.put("account", "zhangsan");
        model.put("name", "张三");
        params.put("model", model);

        System.out.println(ExprUtil.getProperty(params, "printDate"));
        System.out.println(ExprUtil.getProperty(params, "model.account"));
        System.out.println(ExprUtil.getProperty(params, "model.name"));
    };

    @Test
    public void getExprValueBySimpleTest(){
        Map<String, Object> params = new HashMap<>();

        params.put("printDate", LocalDateTime.now().toString().replace("T", " "));

        Map<String, Object> model = new HashMap<>();
        model.put("account", "zhangsan");
        model.put("name", "张三");
        params.put("model", model);

        List<String> listStr = Arrays.asList("aa", "bb", "cc");
        params.put("listStr", listStr);

        System.out.println(ExprUtil.getExprValue(params, "${printDate}"));
        System.out.println(ExprUtil.getExprValue(params, "${model.account}"));
        System.out.println(ExprUtil.getExprValue(params, "${model.name}"));
        System.out.println(ExprUtil.getExprValue(params, "${listStr[0]}"));
        System.out.println(ExprUtil.getExprValue(params, "${listStr[${index}]}"));
    };

    @Test
    public void getExprStrValueBySimpleTest(){
        Map<String, Object> params = new HashMap<>();

        params.put("printDate", LocalDateTime.now().toString().replace("T", " "));

        Map<String, Object> model = new HashMap<>();
        model.put("account", "zhangsan");
        model.put("name", "张三");
        params.put("model", model);

        List<String> listStr = Arrays.asList("aa", "bb", "cc");
        params.put("listStr", listStr);

        System.out.println(ExprUtil.getExprStrValue(params, "hello，${printDate}"));
        System.out.println(ExprUtil.getExprStrValue(params, "${model.account}"));
        System.out.println(ExprUtil.getExprStrValue(params, "${model.name}"));
        System.out.println(ExprUtil.getExprValue(params, "${listStr[0]}"));
        System.out.println(ExprUtil.getExprValue(params, "${listStr[${index}]}"));
    };

}
