package com.sb.stu.java;

import java.util.Random;

/**
 * 参考：
 * String的Intern方法详解 https://www.cnblogs.com/wxgblogs/p/5635099.html
 * 点评：经测试，其说的每一步没发现错误，但是细节处需要注意
 * 注意1. 再看s和 s2 对象。String s = new String("1"); 第一句代码，生成了2个对象。常量池中的“1” 和 JAVA Heap 中的字符串对象。s.intern(); 这一句是 s 对象去常量池中寻找后发现 “1” 已经在常量池里了。
 *   因此s == s2 为false，而s3 == s4为true.
 * 注意事项，测试时，无法打印出String的栈地址(hashcode和getbytes不行)，另外不要使用junit测试，因为测试结果和main方法的不一样，没找到原因。
 */
public class StringInternTest {

    static final int MAX = 1000 * 10000;
    static final String[] arr = new String[MAX];

    public static void main(String[] args) throws Exception {
        Integer[] DB_DATA = new Integer[10];
        Random random = new Random(10 * 10000);
        for (int i = 0; i < DB_DATA.length; i++) {
            DB_DATA[i] = random.nextInt();
        }
        long t = System.currentTimeMillis();
        for (int i = 0; i < MAX; i++) {
            //arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length]));
            arr[i] = new String(String.valueOf(DB_DATA[i % DB_DATA.length])).intern();
        }

        System.out.println((System.currentTimeMillis() - t) + "ms");
        System.gc();
    }

}
