package com.sb.stu.commonfreemarker.constants;

import lombok.AllArgsConstructor;

/**
 *  Freemarker模板文件路径类型常量
 * 编  号：
 * 名  称：FmTempFilePathEnum
 * 描  述：
 * 完成日期：2018/11/18 11:29
 * @author：felix.shao
 */
@AllArgsConstructor
public enum FmTempFilePathEnum {

    REAL_PATH(0, "真实路径"),
    CLASS_PATH(1, "类路径");

    private int type;

    private String name;

}
