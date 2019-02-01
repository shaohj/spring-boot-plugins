package com.sb.stu.npoi.common.bean.write.tag;

import com.sb.stu.npoi.common.consts.SaxExcelConst;
import com.sb.stu.npoi.common.consts.TagEnum;

/**
 * 编  号：
 * 名  称：ForeachTagData
 * 描  述：
 * 完成日期：2019/02/02 00:38
 *
 * @author：felix.shao
 */
public class ForeachTagData extends TagData {

    @Override
    public String getRealExpr() {
        return String.valueOf(value).replace(SaxExcelConst.TAG_KEY + TagEnum.FOREACH_TAG.getKey(), "").trim();
    }

}
