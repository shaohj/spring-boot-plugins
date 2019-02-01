package com.sb.stu.npoi.common.bean.write;

import com.sb.stu.npoi.common.bean.write.tag.TagData;
import lombok.Data;

import java.util.List;

/**
 * 编  号：
 * 名  称：WriteBlock
 * 描  述：
 * 完成日期：2019/02/02 00:32
 *
 * @author：felix.shao
 */
@Data
public class WriteBlock {

    private TagData tagData;

    private List<WriteBlock> writeBlocks;

}
