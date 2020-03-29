package com.sprboot.plugin.emailex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 编  号：
 * 名  称：EmailExApplication
 * 描  述：
 * 完成日期：2020/3/29 19:28
 * @author：felix.shao
 */
@SpringBootApplication
public class EmailExApplication {

    public static void main(String[] args) {
        //JavaMail 邮件附件名乱码问题 (长度问题) 参考：https://blog.csdn.net/qq_29407009/article/details/78557886
        System.setProperty("mail.mime.splitlongparameters","false");

        SpringApplication.run(EmailExApplication.class, args);
    }

}
