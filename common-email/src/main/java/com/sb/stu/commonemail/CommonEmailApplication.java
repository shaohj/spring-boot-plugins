package com.sb.stu.commonemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 编  号：
 * 名  称：CommonEmailApplication
 * 描  述：
 * 完成日期：2018/8/4 15:14
 * @author：felix.shao
 */
@SpringBootApplication
public class CommonEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonEmailApplication.class, args);

        //JavaMail 邮件附件名乱码问题 (长度问题) 参考：https://blog.csdn.net/qq_29407009/article/details/78557886
        System.setProperty("mail.mime.splitlongparameters","false");
    }
}
