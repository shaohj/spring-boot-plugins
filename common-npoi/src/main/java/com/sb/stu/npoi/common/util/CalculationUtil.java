package com.sb.stu.npoi.common.util;

/**
 * 编  号：
 * 名  称：CalculationUtil
 * 描  述：
 * 完成日期：2019/01/30 23:43
 *
 * @author：felix.shao
 */
public class CalculationUtil {

    /** hashmap默认加载因子 */
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public static int calMapCapacity(int capacity){
        return ((int)(capacity * DEFAULT_LOAD_FACTOR) + 1);
    }

}
