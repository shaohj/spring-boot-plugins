package com.sb.stu.commonnet.nio.general;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * nio客户端，有多个NioClient，但是只有一个NioClientHandler(类变量)
 * 编  号：
 * 名  称：NioClient
 * 描  述：
 * 完成日期：2018/9/7 19:32
 * @author：felix.shao
 */
public class NioClient {

    private static final Logger logger = LoggerFactory.getLogger(NioClient.class);

    /** nio客户端处理线程，类变量 */
    private static NioClientHandler clientHandle;

    public static void start(){
        start(NioConstant.DEFAULT_SERVER_IP, NioConstant.DEFAULT_SERVER_PORT);
    }

    public static synchronized void start(String ip, int port){
        if(clientHandle != null){
            clientHandle.stop();
        }
        clientHandle = new NioClientHandler(ip, port);
        new Thread(clientHandle,"NioClientHandler").start();
    }

    public static boolean sendMsg(String msg) throws Exception{
        clientHandle.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) {
        /**
         * 0|13300000001|张三
         */
        start();

        /** 也可以提供绑定，发送消息，关闭接口给调用 */
        logger.info("与服务器通讯,请录入消息体，格式为：类型|message\r\n" +
                "-->支持的消息体格式如下：\r\n" +
                "* 0|phone|用户名\r\n" +
                "* 1|msg发送普通文本消息\r\n" +
                "* 2获取当前所有连接信息\r\n" +
                "* 3关闭连接");
        Scanner scanner = new Scanner(System.in);
        try{
            while(NioClient.sendMsg(scanner.nextLine())){

            };
        } catch (Exception e){
            logger.error("", e);
        }
    }

}
