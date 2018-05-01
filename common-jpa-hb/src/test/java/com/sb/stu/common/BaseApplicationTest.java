package com.sb.stu.common;

import com.sb.stu.commonjpahb.CommonJpaHbApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonJpaHbApplication.class)
@ActiveProfiles
public class BaseApplicationTest {

    private Logger logger = LoggerFactory.getLogger(BaseApplicationTest.class);

    @Test
    public void contextLoads() {
        logger.info("<<<<<<<<  contextLoads,{}", "success");
    }

}
