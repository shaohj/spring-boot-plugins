package com.sprboot.plugin.freemarkerex.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 编  号：
 * 名  称：IOUtils
 * 描  述：
 * 完成日期：2018/11/18 23:54
 *
 * @author：felix.shao
 */
public class IOUtils {

    public static void copy(InputStream inp, OutputStream out) throws IOException {
        byte[] buff = new byte[4096];
        int count;
        while ((count = inp.read(buff)) != -1) {
            if (count > 0) {
                out.write(buff, 0, count);
            }
        }
    }

}
