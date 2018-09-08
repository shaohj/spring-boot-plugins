package com.sb.stu.commonnet.nio.general;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 编  号：
 * 名  称：NioServer
 * 描  述：
 * 完成日期：2018/9/7 16:00
 * @author：felix.shao
 */
public class NioServer {

    private static final Logger logger = LoggerFactory.getLogger(NioServer.class);

    /** 存放了当前所有客户的连接信息。key为客户手机号 */
    public static final ConcurrentHashMap<String, SocketChannel> clients = new ConcurrentHashMap(8);

    /** 单例模式存放服务器对象 */
    private static volatile NioServer singleServer;

    /** nio服务器端处理线程 */
    private NioServerHandler serverHandle;

    private NioServer(){

    }

    /** 服务端是否启动标志位 */
    @Getter
    @Setter
    private volatile boolean startFlag = false;

    public static NioServer singleServer() {
        if(null == singleServer){
            synchronized (NioServer.class){
                if(null == singleServer){
                    logger.info("create NioServer.singleServer");
                    singleServer = new NioServer();
                }
            }
        }
        return singleServer;
    }

    public synchronized void start(int port){
        if(startFlag){
            logger.info("NioServer has start");
            return ;
        } else {
            startFlag = true;
        }
        logger.info("create and init NioServer.singleServer.serverHandle port={}", port);
        serverHandle = new NioServerHandler(port);
        logger.info("start serverHandle monitor thread");
        new Thread(serverHandle,"NioServerHandler").start();
    }

    public static void main(String[] args){
        singleServer().start(NioConstant.DEFAULT_SERVER_PORT);
    }

}
