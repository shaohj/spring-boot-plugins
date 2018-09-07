package com.sb.stu.commonnet.nio.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 编  号：
 * 名  称：NioClient
 * 描  述：
 * 完成日期：2018/9/7 19:32
 * @author：felix.shao
 */
public class NioClient {

    private static final Logger logger = LoggerFactory.getLogger(NioClient.class);

    /** nio客户端处理线程 */
    private static NioClientHandler clientHandle;

    public static void start(){
        start(NioConstant.DEFAULT_SERVER_IP, NioConstant.DEFAULT_SERVER_PORT);
    }

    public static synchronized void start(String ip, int port){
        if(clientHandle != null){
            clientHandle.stop();
        }
        clientHandle = new NioClientHandler(ip, port);
        new Thread(clientHandle,"Server").start();
    }
    public static boolean sendMsg(String msg) throws Exception{
        if(msg.equals("q")){
            return false;
        }
        clientHandle.sendMsg(msg);
        return true;
    }
    public static void main(String[] args){
        start();
    }

}
