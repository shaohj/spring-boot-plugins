package com.sb.stu.commonmuldbmb.mapper.db2;

import com.alibaba.fastjson.JSON;
import com.sb.stu.common.BaseApplicationTest;
import com.sb.stu.commonmuldbmb.model.db2.MoreDb2Table;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 名  称：<br/>
 * 描  述：<br/>
 * 完成日期： <br/>
 * 编码作者：<br/>
 */
public class MoreDb2TableMapperTest extends BaseApplicationTest {

    private static final Logger logger = LoggerFactory.getLogger(MoreDb2TableMapperTest.class);

    private @Autowired MoreDb2TableMapper db2TableMapper;

    @Test
    public void selectByNumberTest(){
        MoreDb2Table result = db2TableMapper.selectByPrimaryKey(1);
        logger.info("result={}", JSON.toJSONString(result));
    }

}
