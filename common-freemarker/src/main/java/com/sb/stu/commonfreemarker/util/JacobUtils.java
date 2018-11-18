package com.sb.stu.commonfreemarker.util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import lombok.extern.slf4j.Slf4j;

/**
 * 编  号：
 * 名  称：JacobUtil
 * 描  述：
 * 完成日期：2018/11/18 21:10
 *
 * @author：felix.shao
 */
@Slf4j
public class JacobUtils {

    /**
     * docToXml<br />
     *   doc转xml，至于docx转xml我们功能不需要，具体结果暂没测试这么细<br />
     * @param filePath : doc文件路径
     * @param xmlFilePath : doc转的xml文件路径
     * @return void
     * @author felix.shao
     * @since 2018/11/18 21:35
     */
    public static void docToXml(String filePath, String xmlFilePath){
        //启动word
        ActiveXComponent app = new ActiveXComponent( "Word.Application");
        //为false时设置word不可见，为true时是可见要不然看不到Word打打开文件的过程
        app.setProperty("Visible", new Variant(false));
        Dispatch docs = app.getProperty("Documents").toDispatch();
        //打开编辑器，打开word文档
        Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method, new Object[] {filePath, new Variant(false), new Variant(true)} , new int[1]).toDispatch();
        //xml文件格式宏11
        Dispatch.call(doc, "SaveAs", xmlFilePath, 11);
        Dispatch.call(doc, "Close", false);
        app.invoke("Quit",0);
        log.info("word格式转为xml格式完成");
    }

}
