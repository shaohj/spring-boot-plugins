package com.sb.stu.java;

/**
 * 参考：
 * String的Intern方法详解 https://www.cnblogs.com/wxgblogs/p/5635099.html
 * 点评：经测试，其说的每一步没发现错误，但是细节处需要注意
 * 注意1. 再看s和 s2 对象。String s = new String("1"); 第一句代码，生成了2个对象。常量池中的“1” 和 JAVA Heap 中的字符串对象。s.intern(); 这一句是 s 对象去常量池中寻找后发现 “1” 已经在常量池里了。
 *   因此s == s2 为false，而s3 == s4为true.
 * 注意事项，测试时，像打印出String的栈地址，发现不可行(hashcode和getbytes不行)，另外不要使用junit测试，因为测试结果不准，没找到原因。
 */
public class StringMainTest {

    public static void main(String[] args) {
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2); //false

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4); //true

        String s5 = new String("1") + new String("1");
        s5.intern(); //s3已经intern了，这里常量池的不会指向s5，还是指向s3了。
        String s6 = "11";
        System.out.println(s5 == s6); //false
        System.out.println(s3 == s6); //true
    }

}
