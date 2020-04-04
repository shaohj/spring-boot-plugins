package com.sprboot.plugin.redisuse.type.list;

import com.alibaba.fastjson.JSON;
import com.sprboot.plugin.redisuse.common.BaseApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Slf4j
public class ListRedisTemplateTest extends BaseApplicationTest {

    @Autowired
    private RedisTemplate<String, String> template;

    @Test
    public void testListRedisTemplate() {
        ListOperations<String, String> listOpt = template.opsForList();
        
        listOpt.leftPush("ul", "zs");
        listOpt.leftPush("ul", "ls");
        listOpt.leftPush("ul", "ww");
        
        List<String> result = listOpt.range("ul", 0, -1);
        
        log.info("resultStr={}", JSON.toJSONString(result));
    }
    
}
