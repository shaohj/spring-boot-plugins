package com.sb.stu.test.type.list;

import java.util.List;

import com.sb.stu.common.BaseApplicationTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;

public class ListRedisTemplateTest extends BaseApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(ListRedisTemplateTest.class);
    
    private @Autowired RedisTemplate<String, String> template;

    @Test
    public void testListRedisTemplate() {
        ListOperations<String, String> listOpt = template.opsForList();
        
        listOpt.leftPush("ul", "zs");
        listOpt.leftPush("ul", "ls");
        listOpt.leftPush("ul", "ww");
        
        List<String> result = listOpt.range("ul", 0, -1);
        
        logger.info("resultStr={}", JSON.toJSONString(result));
    }
    
}
