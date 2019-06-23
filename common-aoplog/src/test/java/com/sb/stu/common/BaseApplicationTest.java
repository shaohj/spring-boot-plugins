package com.sb.stu.common;

import com.sb.stu.commonaoplog.CommonAoplogApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 编  号：
 * 名  称：BaseApplicationTest
 * 描  述：
 * 完成日期：2019/6/23 12:31
 * @author：felix.shao
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonAoplogApplication.class)
@ActiveProfiles
public class BaseApplicationTest {

    private Logger logger = LoggerFactory.getLogger(BaseApplicationTest.class);

    @Test
    public void contextLoads() {
        logger.info("<<<<<<<<  contextLoads,{}", "success");
    }

}
