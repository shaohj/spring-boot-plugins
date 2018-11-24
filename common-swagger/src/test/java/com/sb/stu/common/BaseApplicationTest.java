package com.sb.stu.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.sb.stu.commonswagger.CommonSwaggerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonSwaggerApplication.class)
@ActiveProfiles
public class BaseApplicationTest {

    private Logger logger = LoggerFactory.getLogger(BaseApplicationTest.class);

    @Test
    public void contextLoads() {
        logger.info("<<<<<<<<  contextLoads,{}", "success");
    }

}
