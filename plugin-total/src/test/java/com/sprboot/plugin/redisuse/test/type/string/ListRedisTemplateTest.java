package com.sprboot.plugin.redisuse.test.type.string;

import com.alibaba.fastjson.JSON;
import com.sprboot.plugin.redisuse.common.BaseApplicationTest;
import com.sprboot.plugin.redisuse.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ListRedisTemplateTest extends BaseApplicationTest {

    @Autowired
    private RedisTemplate<String, List<User>> template;

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
        log.info("result={}", JSON.toJSONString(result));
    }
    
}
