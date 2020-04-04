package com.sprboot.plugin.redisuse.common;

import com.sprboot.plugin.redisuse.RedisUseApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisUseApplication.class)
@ActiveProfiles("redisuse")
public class BaseApplicationTest {

    @Test
    public void contextLoads() {
        log.info("\n--><<<<<<<<  contextLoads,{}", "success");
    }

}
