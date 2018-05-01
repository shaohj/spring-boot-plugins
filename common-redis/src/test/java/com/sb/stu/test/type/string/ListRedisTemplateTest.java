package com.sb.stu.test.type.string;

import java.util.ArrayList;
import java.util.List;

import com.sb.stu.commonredis.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.sb.stu.common.BaseApplicationTest;

public class ListRedisTemplateTest extends BaseApplicationTest {

    private static Logger logger = LoggerFactory.getLogger(ListRedisTemplateTest.class);
    
    private @Autowired RedisTemplate<String, List<User>> template;

    @Test
    public void testListRedisTemplate() {
        List<User> users = new ArrayList<>();
        User user01 = new User("测试用户01", 100);
        User user02 = new User("测试用户02", 101);
        
        users.add(user01);
        users.add(user02);
        
        template.opsForValue().set("users", users);
        //原本opsForValue()是只能操作字符串的.现在就可以操作对象了
        List<User> result = template.opsForValue().get("users");
        logger.info("result={}", JSON.toJSONString(result));
    }
    
}
