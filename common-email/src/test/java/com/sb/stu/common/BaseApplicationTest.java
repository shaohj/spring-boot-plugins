package com.sb.stu.common;

import com.sb.stu.commonemail.CommonEmailApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonEmailApplication.class)
@ActiveProfiles
public class BaseApplicationTest {

    @Test
    public void contextLoads() {
        log.info("<<<<<<<<  contextLoads,{}", "success");
    }

}

