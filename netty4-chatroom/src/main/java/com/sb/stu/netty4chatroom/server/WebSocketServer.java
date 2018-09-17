package com.sb.stu.netty4chatroom.server;

import com.sb.stu.netty4chatroom.config.NettyServerConfigs;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.*;

/**
 * 编  号：
 * 名  称：WebSocketServer
 * 描  述：
 * 完成日期：2018/09/17 19:36
 *
 * @author：felix.shao
 */
@Service
public class WebSocketServer {

    private Channel serverChannel;

    @Autowired
    private NettyServerConfigs config;

    @Autowired
    private TextWebSocketFrameHandler textWebSocketFrameHandler;

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(config.getBossCount());
        EventLoopGroup workerGroup = new NioEventLoopGroup(config.getWorkerCount());

        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
                        pipeline.addLast(new ChunkedWriteHandler());
                        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                        pipeline.addLast(textWebSocketFrameHandler);
                    }
                });
        Map<ChannelOption<?>, Object> tcpChannelOptions = new HashMap<ChannelOption<?>, Object>();
        tcpChannelOptions.put(ChannelOption.SO_KEEPALIVE, config.isKeepAlive());
        tcpChannelOptions.put(ChannelOption.SO_BACKLOG, config.getBacklog());

        Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
        for (@SuppressWarnings("rawtypes") ChannelOption option : keySet) {
            b.option(option, tcpChannelOptions.get(option));
        }

        /** 启动服务器 */
        InetSocketAddress srvAddress = new InetSocketAddress(config.getTcpPort());
        serverChannel = b.bind(srvAddress).sync().channel().closeFuture().sync().channel();
    }

    @PreDestroy
    public void stop() throws Exception {
        serverChannel.close();
        serverChannel.parent().close();
    }

}
