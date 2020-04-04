package com.sprboot.ex.ormplugin.mybatis.single.common;

import com.sprboot.ex.ormplugin.mybatis.single.MybatisSingleApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisSingleApplication.class)
@ActiveProfiles("mybatis-single")
public class BaseApplicationTest {

    @Test
    public void contextLoads() {
        log.info("\n--><<<<<<<<  contextLoads,{}", "success");
    }

}
