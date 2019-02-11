package com.sb.stu.npoi.common.consts;

import cn.hutool.core.util.StrUtil;

/**
 * 编  号：
 * 名  称：TagEnum
 * 描  述：
 * 完成日期：2019/02/01 23:45
 *
 * @author：felix.shao
 */
public enum TagEnum {

    CONST_TAG(0, null, "非标签，常量与表达式混合的字符串", false),
    IF_TAG(1, "if", "if标签", true),
    EACH_TAG(1, "each", "each标签", false),
    FOREACH_TAG(0, "foreach", "普通foreach标签", true),
    BIGFOREACH_TAG(0, "pageforeach", "分页foreach标签", true),
    ;

    private int type;

    private String key;

    private String name;

    private boolean hasEndTag;

    TagEnum(int type, String key, String name, boolean hasEndTag) {
        this.type = type;
        this.key = key;
        this.name = name;
        this.hasEndTag = hasEndTag;
    }

    /**
     * 判断表达式是否为TagEnum
     * @param expr
     * @return
     */
    public static TagEnum getTagEnum(String expr){
        if(StrUtil.isEmpty(expr)){
            return CONST_TAG;
        }
        for (TagEnum tagEnum : TagEnum.values()){
            if(!StrUtil.isEmpty(tagEnum.key) && expr.startsWith(SaxExcelConst.TAG_KEY + tagEnum.key + " ")){
                return tagEnum;
            }
        }
        return CONST_TAG;
    }

    public static boolean isEndTagNum(String expr){
        if(StrUtil.isEmpty(expr)){
            return false;
        }
        return expr.trim().equalsIgnoreCase(SaxExcelConst.TAG_KEY + "end");
    }

    public static void main(String[] args) {
        System.out.println(getTagEnum(null));
        System.out.println(getTagEnum("#if aa"));
    }

    public int getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public boolean isHasEndTag() {
        return hasEndTag;
    }

}
