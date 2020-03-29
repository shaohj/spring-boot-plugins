package com.sprboot.plugin.freemarkerex.constants;

import freemarker.template.Version;

/**
 * 编  号：
 * 名  称：FreemarkConstants
 * 描  述：
 * 完成日期：2020/3/29 20:12
 * @author：felix.shao
 */
public class FreemarkConstants {

    public static final Version VERSION = new Version("2.3.26");

    /** word存放的真实目录 */
    public static final String WORD_TEMP_BASE_REALPATH = "E:\\temp\\word_template\\";

    /** word的ftl模板文件存放目录 */
    public static final String FTL_PATH = "ftl";

    /** word的doc模板文件存放目录 */
    public static final String WORD_DOC_TEMPLATE_PATH = "doc";

    /** word的docx模板文件存放目录 */
    public static final String WORD_DOCX_TEMPLATE_PATH = "docx";

    /** XML临时文件目录 */
    public static final String XML_TEMPORARY_PATH = "E:\\temp\\wordExport\\";

}
