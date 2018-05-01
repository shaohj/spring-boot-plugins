package com.sb.stu.test.type.string;

import com.sb.stu.common.BaseApplicationTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class StringRedisTemplateTest extends BaseApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(StringRedisTemplateTest.class);

    private @Autowired StringRedisTemplate stringTemplate;

    @Test
    public void testStringRedisTemplate() throws Exception {
        // 保存字符串
        stringTemplate.opsForValue().set("strTest", "222");
        ValueOperations<String, String>  valOpt = stringTemplate.opsForValue();
        String val = valOpt.get("strTest");
        Assert.assertEquals("222", val);

        logger.info("strTest.val={}", val);
    }
    
    
}
