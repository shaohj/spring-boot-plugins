## email-ex

### 拓展描述
基于spring-boot-starter-mail的发送邮件代码封装。

~~~
1.引入依赖
<!-- 本地开发时发送邮件使用,javaEE仅提供规范 -->
<dependency>
    <groupId>javax.mail</groupId>
    <artifactId>mail</artifactId>
    <version>1.4.1</version>
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
    <scope>test</scope>
</dependency>

2.示例代码
com.sprboot.plugin.emailex

3.示例测试代码
com.sprboot.plugin.emailex
~~~

### 笔记

### 资料
* [JavaMail学习笔记（三）、使用SMTP协议发送电子邮件（全）](http://www.2cto.com/kf/201206/136649.html)
