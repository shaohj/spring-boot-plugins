package com.sprboot.plugin.freemarkerex.util;

import com.alibaba.fastjson.util.IOUtils;
import com.sprboot.plugin.freemarkerex.constants.FmTempFilePathEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipException;

import static com.sprboot.plugin.freemarkerex.constants.FreemarkConstants.*;

/**
 * 编  号：
 * 名  称：WordUtils
 * 描  述：
 * 完成日期：2020/3/29 20:12
 * @author：felix.shao
 */
@Slf4j
public class WordUtils {

    /**
     * parseDoc
     * @param fmEnum : 模板文件路径类型
     * @param msgMap javabean
     * @param fromPath : 源文件路径
     * @param toPath : 新文件路径
     * @return void
     * @author felix.shao
     * @since 2018/11/18 22:43
     */
    public static void parseDoc(FmTempFilePathEnum fmEnum, Map<String, Object> msgMap, String fromPath, String toPath) throws IOException{
        /** 将doc转为xml文件(_01等可以保留做毫秒并发数处理) */
        String fileName = Java8DateUtils.getYyyyMmDDHhMmSs() + "_01.ftl";
        String ftlTempFile = WORD_TEMP_BASE_REALPATH + FTL_PATH + "\\" + fileName;
        try {
            JacobUtils.docToXml(fromPath, ftlTempFile);
            FreeMarkerUtil.parseTFToFile(fmEnum, msgMap, fileName, toPath);
        } finally {
            /** 删除临时文件 */
            FileUtils.deleteFile(ftlTempFile);
        }

        log.info("parseDoc success.\nfromPath-->{}\ntoPath-->[{}] success,", fromPath, toPath);
    }

    /**
     * 替换模板文件的word/document.xml,生成新的docx,String导出会长度溢出
     * @param msgMap javabean
     * @param fromPath : 源文件路径
     * @param toPath : 新文件路径
     * @throws ZipException
     * @throws IOException
     */
    public static void parseDocx(FmTempFilePathEnum fmEnum, Map<String, Object> msgMap, String fromPath, String toPath) throws ZipException, IOException {
        URL url = null;
        switch (fmEnum){
            case REAL_PATH: url = new File(fromPath).toURI().toURL(); break;
            case CLASS_PATH: url = WordUtils.class.getClassLoader().getResource(fromPath); break;
            default:return;
        }

        ZipFile zipFile = null;
        ZipOutputStream zipout = null;

        /** 创建导出父文件夹 */
        FileUtils.createParentFolder(toPath);

        try {
            zipFile = new ZipFile(URLDecoder.decode(url.getPath(), "UTF-8"), "UTF-8");
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.getEntries();
            zipout = new ZipOutputStream(new FileOutputStream(toPath));
            zipout.setEncoding("UTF-8");
            int len = -1;
            byte[] buffer=new byte[1024];
            while(zipEntrys.hasMoreElements()) {
                ZipEntry next = zipEntrys.nextElement();
                InputStream is = zipFile.getInputStream(next);
                //把输入流的文件传到输出流中 如果是word/document.xml由我们输入
                zipout.putNextEntry(new ZipEntry(next.toString()));
                if("word/document.xml".equals(next.toString())){
                    /** 在模板文件目录下生成模板文件 */
                    String fileName = Java8DateUtils.getYyyyMmDDHhMmSs() + "_01.ftl";
                    String newFileName = Java8DateUtils.getYyyyMmDDHhMmSs() + "_01_new.ftl";
                    String ftlTempFile = WORD_TEMP_BASE_REALPATH + FTL_PATH + "\\" + fileName;
                    String ftlNewFile = WORD_TEMP_BASE_REALPATH + FTL_PATH + "\\" + newFileName;
                    FileUtils.createFile(ftlTempFile);
                    FileUtils.createFile(ftlNewFile);
                    File ftlFile = new File(ftlTempFile);
                    FileOutputStream ftlOut = null;
                    try {
                        ftlOut = new FileOutputStream(ftlFile);
                        com.sprboot.plugin.freemarkerex.util.IOUtils.copy(is, ftlOut);
                        IOUtils.close(ftlOut);
                        /** 替换模板表达式，生成替换后的文件 */
                        FreeMarkerUtil.parseTFToFile(fmEnum, msgMap, fileName, ftlNewFile);
                        /** 将替换后的文件写入压缩文件中 */
                        InputStream in = new FileInputStream(ftlNewFile);
                        while((len = in.read(buffer))!=-1){
                            zipout.write(buffer,0,len);
                        }
                        IOUtils.close(in);
                    } finally {
                        IOUtils.close(ftlOut);
                        /** 删除临时文件 */
                        FileUtils.deleteFile(ftlTempFile);
                        FileUtils.deleteFile(ftlNewFile);
                    }
                }else {
                    while((len = is.read(buffer))!=-1){
                        zipout.write(buffer,0,len);
                    }
                    IOUtils.close(is);
                }
            }
            log.info("parseDocx success.\nfromPath-->{}\ntoPath-->[{}] success,", fromPath, toPath);
        } catch (FileNotFoundException e) {
            log.error("Error by parseDocx.{}", e);
        } finally{
            IOUtils.close(zipout);
            IOUtils.close(zipFile);
        }
    }

}
