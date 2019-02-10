package com.sb.stu.npoi.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import static com.sb.stu.npoi.common.consts.SaxExcelConst.EXPR_END;
import static com.sb.stu.npoi.common.consts.SaxExcelConst.EXPR_START;

/**
 * 编  号：
 * 名  称：ExprUtil
 * 描  述：
 * 完成日期：2019/02/01 01:18
 *
 * @author：felix.shao
 */
@Slf4j
public class ExprUtil {

    public static void main(String[] args) {
        String content = "hello ${name}, 1 2 3 4 5 ${six} 7, again ${name}. ";
        Map<String, Object> map = new HashMap<>();
        map.put("name", "java");
        map.put("six", "6");

        System.out.println(parseTempStr(map, content));
    }

    public static Object parseTempStr(final Map<String, Object> params, Object value){
        if(null == value || !"java.lang.String".equals(value.getClass().getName())){
            return value;
        }
        Object result = ExprUtil.getExprStrValue(params, value.toString());
        return result;
    }

    /**
     * 根据java对象动态替换表达式里面的值
     * @param context
     * @param exprStr 含有表达式的字符串，格式可以为*${model[${index}]}，*${model}，注意目前嵌套表达式还未支持，后续可拓展支持
     */
    public static Object getExprStrValue(Object context, String exprStr){
        /** valueCount为exprStr中临时存放表达式的数量，exprCount为已处理的表达式数量 */
        int valueCount = 0;
        int exprCount = 0;

        /** exprStr待处理的表达式开始和结束索引 */
        int valueFrom = -1;
        int valueTo = -1;

        /** 临时索引 */
        int pos = 0;

        Object value = null;

        int startIdx = exprStr.indexOf(EXPR_START);
        int endIdx = exprStr.lastIndexOf(EXPR_END);

        /** 判断是不是只是一个表达式 */
        boolean bJustExpr = exprStr.length() == endIdx + EXPR_END.length() - startIdx;

        while (pos < exprStr.length()) {
            if (pos + EXPR_START.length() <= exprStr.length()) {
                if (EXPR_START.equals(exprStr.substring(pos, pos + EXPR_START.length()))) {
                    if (valueCount == 0) {
                        valueFrom = pos;
                    }
                    valueCount++;
                    pos = pos + EXPR_START.length();
                    continue;
                }
            }

            if (EXPR_END.equals(exprStr.substring(pos, pos + EXPR_END.length()))) {
                valueCount--;
                if (valueCount == 0) {
                    valueTo = pos;
                    String expr = exprStr.substring(valueFrom, valueTo + EXPR_END.length());
                    value = getExprValue(context, expr);
                    exprCount++;
                    // replace the string
                    StringBuffer sbuf = new StringBuffer(exprStr);
                    if (null != value) {
                        String rep = value.toString();
                        sbuf.replace(valueFrom, valueTo + EXPR_END.length(), rep);
                        pos += EXPR_END.length() + value.toString().length() - expr.length();
                    } else {
                        String rep = "";
                        sbuf.replace(valueFrom, valueTo + EXPR_END.length(), rep);
                        pos += EXPR_END.length() + 0 - expr.length();
                    }
                    exprStr = sbuf.toString();
                    continue;
                } else {
                    pos += EXPR_END.length();
                    continue;
                }
            }
            pos++;
        }

        if (exprCount == 1 && bJustExpr) {
            return value;
        } else {
            return exprStr;
        }
    }

    /**
     * 根据java对象动态替换表达式里面的值
     * @param context
     * @param expr 只有表达式的字符串，格式可以为${model[${index}]}，${model}，注意目前嵌套表达式还未支持，后续可拓展支持
     */
    public static Object getExprValue(Object context, String expr){
        int startIdx = expr.indexOf(EXPR_START);
        int endIdx = expr.lastIndexOf(EXPR_END);

        Object exprValue = null;

        //判断是否为${*}的表达式
        if(0 == startIdx && 0 < endIdx){
            String property = expr.substring(startIdx + EXPR_START.length(), endIdx);

            // 内部还有表达式，则递归处理表达式
            if (property.indexOf(EXPR_START) >= 0) {
                Object exprValueTemp = getExprValue(context, property);
                if (null != exprValueTemp){
                    property = exprValueTemp.toString();
                }
            }
            exprValue = getProperty(context, property);
        }
        return exprValue;
    }

    /**
     * 调用PropertyUtils获取对应的对象，包装一层，处理表达式转换异常
     * @param context 如传map
     * @param name javabean或map对应的key值，如printDate、model.account
     * @return javabean或map对应的value
     */
    public static Object getProperty(Object context, String name) {
        Object value = null;
        try {
            value = PropertyUtils.getProperty(context, name);
        } catch (Exception e) {
            log.warn("getProperty exception.{}", e.getMessage());
        }
        return value;
    }

    public static Iterator getIterator(Object collection) {
        Iterator iterator = null;
        if (collection.getClass().isArray()) {
            try {
                // If we're lucky, it is an array of objects
                // that we can iterate over with no copying
                iterator = Arrays.asList((Object[]) collection).iterator();
            } catch (ClassCastException e) {
                // Rats -- it is an array of primitives
                int length = Array.getLength(collection);
                ArrayList c = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    c.add(Array.get(collection, i));
                }
                iterator = c.iterator();
            }
        } else if (collection instanceof Collection) {
            iterator = ((Collection) collection).iterator();
        } else if (collection instanceof Iterator) {
            iterator = (Iterator) collection;
        } else if (collection instanceof Map) {
            iterator = ((Map) collection).entrySet().iterator();
        }
        return iterator;
    }

    public static Field[] getBeanProperties(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        String m = "";

        for (int i = 0; i < methods.length; i++) {
            m += methods[i].getName() + ",";
        }

        List flist = new ArrayList();
        for (int i = 0; i < fields.length; i++) {
            if (m.indexOf("get" + fields[i].getName().substring(0, 1).toUpperCase()
                    + fields[i].getName().substring(1, fields[i].getName().length())) >= 0) {
                flist.add(fields[i]);
            }
        }
        Field[] result = new Field[flist.size()];
        flist.toArray(result);
        return result;
    }

}
