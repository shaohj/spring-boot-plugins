package com.sprboot.plugin.redisuse.type.string;

import com.alibaba.fastjson.JSON;
import com.sprboot.plugin.redisuse.common.BaseApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ListMapRedisTemplateTest extends BaseApplicationTest {

    @Autowired
    private RedisTemplate<String, List<Map<String,Object>>> listMapTemplate;

    @Test
    public void testListRedisTemplate() {
        List<Map<String,Object>> ms = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("name", "rs");
        map.put("age", 20);
    
        Map<String,Object> map1 = new HashMap<>();
        map1.put("name", "rs1");
        map1.put("age", 21);
    
        Map<String,Object> map2 = new HashMap<>();
        map2.put("name", "rs2");
        map2.put("age", 22);
    
        ms.add(map);
        ms.add(map1);
        ms.add(map2);
        listMapTemplate.opsForValue().set("key_ml", ms);
        List<Map<String,Object>> mls = listMapTemplate.opsForValue().get("key_ml");
        log.info("mls={}", JSON.toJSONString(mls));
    }
    
}
