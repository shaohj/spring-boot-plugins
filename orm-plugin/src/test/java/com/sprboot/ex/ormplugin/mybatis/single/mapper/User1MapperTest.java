package com.sprboot.ex.ormplugin.mybatis.single.mapper;

import com.alibaba.fastjson.JSON;
import com.sprboot.ex.ormplugin.mybatis.single.model.User1;
import com.sprboot.ex.ormplugin.mybatis.single.common.BaseApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 编  号：
 * 名  称：User1MapperTest
 * 描  述：
 * 完成日期：2020/4/4 19:18
 * @author：felix.shao
 */
@Slf4j
public class User1MapperTest extends BaseApplicationTest {

    @Autowired
    private User1Mapper user1Mapper;

    @Test
    public void selectByIdTest(){
        String number = "1";
        User1 user1 = user1Mapper.selectById(number);
        log.info("\n-->user1 = {}", JSON.toJSONString(user1));
    }

}
