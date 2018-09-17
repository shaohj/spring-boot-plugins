package com.sb.stu.netty4chatroom;

import com.sb.stu.netty4chatroom.server.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Netty4ChatroomApplication {

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext context = SpringApplication.run(Netty4ChatroomApplication.class, args);
        WebSocketServer webSocketServer = context.getBean(WebSocketServer.class);
        webSocketServer.run();
    }

}
