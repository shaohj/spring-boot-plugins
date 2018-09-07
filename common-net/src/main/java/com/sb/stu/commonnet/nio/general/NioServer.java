package com.sb.stu.commonnet.nio.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编  号：
 * 名  称：NioServer
 * 描  述：
 * 完成日期：2018/9/7 16:00
 * @author：felix.shao
 */
public class NioServer {

    private static final Logger logger = LoggerFactory.getLogger(NioServer.class);

    /** nio服务器端处理线程 */
    private static NioServerHandler serverHandle;

    public static void start(){
        start(NioConstant.DEFAULT_SERVER_PORT);
    }

    public static synchronized void start(int port){
        if(null != serverHandle){
            serverHandle.stop();
        }
        serverHandle = new NioServerHandler(port);
        new Thread(serverHandle,"Server").start();
    }

    public static void main(String[] args){
        start();
    }

}
