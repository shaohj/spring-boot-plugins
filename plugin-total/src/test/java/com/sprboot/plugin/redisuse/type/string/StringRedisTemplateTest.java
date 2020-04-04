package com.sprboot.plugin.redisuse.type.string;

import com.sprboot.plugin.redisuse.common.BaseApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@Slf4j
public class StringRedisTemplateTest extends BaseApplicationTest {

    @Autowired
    private StringRedisTemplate stringTemplate;

    @Test
    public void testStringRedisTemplate() throws Exception {
        // 保存字符串
        stringTemplate.opsForValue().set("strTest", "222");
        ValueOperations<String, String>  valOpt = stringTemplate.opsForValue();
        String val = valOpt.get("strTest");
        Assert.assertEquals("222", val);

        log.info("\n-->strTest.val={}", val);
    }
    
    
}
