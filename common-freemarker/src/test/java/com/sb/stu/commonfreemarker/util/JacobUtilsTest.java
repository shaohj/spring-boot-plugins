package com.sb.stu.commonfreemarker.util;

import org.junit.Test;

/**
 * 编  号：
 * 名  称：JacobUtilsTest
 * 描  述：
 * 完成日期：2018/11/18 21:44
 *
 * @author：felix.shao
 */
public class JacobUtilsTest {

    @Test
    public void docToXmlTest(){
        String filePath = "E:\\temp\\word_template\\show\\1.doc";
        String xmlFilePath = "E:\\temp\\word_template\\show\\1.xml";
        JacobUtils.docToXml(filePath, xmlFilePath);
    }

}
