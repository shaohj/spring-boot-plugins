package com.sb.stu.test.type.string;

import com.sb.stu.common.BaseApplicationTest;
import com.sb.stu.commonredis.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;

public class ObjectRedisTemplateTest extends BaseApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(ObjectRedisTemplateTest.class);
    
    private @Autowired RedisTemplate<String, Object> objTemplate;

    @Test
    public void testObjectRedisTemplate() {
        User user = new User("测试用户", 20);
        objTemplate.opsForValue().set(user.getAge()+"", user);
        //原本opsForValue()是只能操作字符串的.现在就可以操作对象了
        Object resultObj = objTemplate.opsForValue().get(String.valueOf(user.getAge()));
        User result = (User) resultObj;
        logger.info("result={}", JSON.toJSONString(result));
    }
    
    
}
