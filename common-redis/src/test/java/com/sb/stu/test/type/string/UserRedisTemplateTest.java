package com.sb.stu.test.type.string;

import com.sb.stu.common.BaseApplicationTest;
import com.sb.stu.commonredis.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;

public class UserRedisTemplateTest extends BaseApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(UserRedisTemplateTest.class);

    @Autowired
    private RedisTemplate<String, User> userTemplate;

    @Test
    public void testUserRedisTemplate() throws Exception {
        // 保存对象
        User user = new User("超人1", 20);
        userTemplate.opsForValue().set(user.getUsername(), user);

        User result = userTemplate.opsForValue().get("超人1");
        Assert.assertEquals(20, result.getAge().longValue());
        
        logger.info("result={}", JSON.toJSONString(result));
    }
    
}
