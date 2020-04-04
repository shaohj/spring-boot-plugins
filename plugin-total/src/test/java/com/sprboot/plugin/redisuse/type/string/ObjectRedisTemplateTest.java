package com.sprboot.plugin.redisuse.type.string;

import com.alibaba.fastjson.JSON;
import com.sprboot.plugin.redisuse.common.BaseApplicationTest;
import com.sprboot.plugin.redisuse.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
public class ObjectRedisTemplateTest extends BaseApplicationTest {

    @Autowired
    private RedisTemplate<String, Object> objTemplate;

    @Test
    public void testObjectRedisTemplate() {
        User user = new User("测试用户", 20);
        objTemplate.opsForValue().set(user.getAge()+"", user);
        //原本opsForValue()是只能操作字符串的.现在就可以操作对象了
        Object resultObj = objTemplate.opsForValue().get(String.valueOf(user.getAge()));
        User result = (User) resultObj;
        log.info("result={}", JSON.toJSONString(result));
    }
    
    
}
