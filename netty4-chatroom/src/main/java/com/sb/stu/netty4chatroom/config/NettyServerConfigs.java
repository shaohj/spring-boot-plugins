package com.sb.stu.netty4chatroom.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 编  号：
 * 名  称：NettyServerConfigs
 * 描  述：
 * 完成日期：2018/09/17 19:31
 *
 * @author：felix.shao
 */
@Data
@Configuration
@PropertySource(value= "classpath:/nettyserver.properties")
public class NettyServerConfigs {

    @Value("${tcp.port}")
    private int tcpPort;

    @Value("${boss.thread.count}")
    private int bossCount;

    @Value("${worker.thread.count}")
    private int workerCount;

    @Value("${so.keepalive}")
    private boolean keepAlive;

    @Value("${so.backlog}")
    private int backlog;

}
