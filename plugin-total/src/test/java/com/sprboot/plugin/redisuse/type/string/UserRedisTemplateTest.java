package com.sprboot.plugin.redisuse.type.string;

import com.alibaba.fastjson.JSON;
import com.sprboot.plugin.redisuse.common.BaseApplicationTest;
import com.sprboot.plugin.redisuse.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class UserRedisTemplateTest extends BaseApplicationTest {

    @Autowired
    private RedisTemplate<String, User> userTemplate;

    @Test
    public void testUserRedisTemplate() throws Exception {
        // 保存对象
        User user = new User("超人1", 20);
        userTemplate.opsForValue().set(user.getUsername(), user);

        User result = userTemplate.opsForValue().get("超人1");
        Assert.assertEquals(20, result.getAge().longValue());
        
        log.info("\n-->result={}", JSON.toJSONString(result));
    }
    
}
