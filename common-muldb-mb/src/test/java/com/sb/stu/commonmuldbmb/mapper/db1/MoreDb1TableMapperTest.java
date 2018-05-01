package com.sb.stu.commonmuldbmb.mapper.db1;

import com.alibaba.fastjson.JSON;
import com.sb.stu.common.BaseApplicationTest;
import com.sb.stu.commonmuldbmb.model.db1.MoreDb1Table;
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
public class MoreDb1TableMapperTest extends BaseApplicationTest {

    private static final Logger logger = LoggerFactory.getLogger(MoreDb1TableMapperTest.class);

    private @Autowired MoreDb1TableMapper db1TableMapper;

    @Test
    public void selectByNumberTest(){
        String number = "1000";
        List<MoreDb1Table> list = db1TableMapper.selectByNumber(number);
        logger.info("list={}", JSON.toJSONString(list));
    }

    @Test
    public void selectByNumberAndNameTest(){
        String number = "1000";
        String name = "张三";
        List<MoreDb1Table> list = db1TableMapper.selectByNumberAndName(number, name);
        logger.info("list={}", JSON.toJSONString(list));
    }

}
