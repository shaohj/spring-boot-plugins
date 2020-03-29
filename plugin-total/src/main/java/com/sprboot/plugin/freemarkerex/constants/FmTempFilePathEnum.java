package com.sprboot.plugin.freemarkerex.constants;

import lombok.AllArgsConstructor;

/**
 * 编  号：
 * 名  称：FmTempFilePathEnum
 * 描  述：Freemarker模板文件路径类型常量
 * 完成日期：2020/3/29 20:09
 * @author：felix.shao
 */
@AllArgsConstructor
public enum FmTempFilePathEnum {

    REAL_PATH(0, "真实路径"),
    CLASS_PATH(1, "类路径");

    private int type;

    private String name;

}
