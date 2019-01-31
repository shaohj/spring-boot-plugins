package com.sb.stu.npoi.common.util;

import static com.sb.stu.npoi.common.consts.SaxExcelConst.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

}
