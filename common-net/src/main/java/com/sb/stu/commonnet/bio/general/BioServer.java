package com.sb.stu.commonnet.bio.general;

import com.alibaba.fastjson.util.IOUtils;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 编  号：
 * 名  称：BioServer
 * 描  述：
 * 完成日期：2018/09/04 20:41
 *
 * @author：felix.shao
 */
public class BioServer {

    private static final Logger logger = LoggerFactory.getLogger(BioServer.class);

    /** 存放了当前所有客户的连接信息。key为客户手机号 */
    public static final ConcurrentHashMap<String, BioServerHandler> clients = new ConcurrentHashMap(4);

    /** 服务端是否启动标志位 */
    @Getter
    @Setter
    private volatile boolean startFlag = false;

    /** 单例的ServerSocket */
    @Getter
    @Setter
    private ServerSocket servSocket;

    /** 单例模式存放服务器对象 */
    private static volatile BioServer singleServer;

    private BioServer(){

    }

    public static BioServer singleServer() throws  IOException{
        if(null == singleServer){
            synchronized (BioServer.class){
                if(null == singleServer){
                    singleServer = new BioServer();
                    try {
                        singleServer.setServSocket(new ServerSocket(BioConstant.DEFAULT_SERVER_PORT));
                    } catch (IOException e) {
                        logger.error("启动服务器失败", e);
                        singleServer = null;
                        throw e;
                    }
                }
            }
        }
        return singleServer;
    }

    public static void start() throws IOException{
        BioServer singleServer = singleServer();
        if(null != singleServer && singleServer.isStartFlag()){
            return ;
        } else{
            singleServer.setStartFlag(true);
        }
        logger.info("启动BioServer，监听端口为{}", BioConstant.DEFAULT_SERVER_PORT);
        while(true){
            logger.info("BioServer等待接收客户端请求...");
            Socket socket = singleServer.getServSocket().accept();
            logger.info("\r\n接收到了新的客户端请求，建立连接成功，创建子线程BioServerHandler处理该请求");
            BioServerHandler handlerThread = new BioServerHandler(socket);
            clients.put(handlerThread.getPhone(), handlerThread);
            new Thread(handlerThread).start();
        }
    }

    public static String getAllCollInfo(){
        return clients.values().stream()
                .map(srvHandler -> srvHandler.getPhone() + "|" + srvHandler.getUserName())
                .collect(Collectors.joining(","));
    }

    public static  void closeConn(BioServerHandler srvHandler){
        if(null != srvHandler){
            logger.info("BioServer关闭连接,phone={},userName={}", srvHandler.getPhone(), srvHandler.getUserName());
            srvHandler.setConnFlag(false);
            IOUtils.close(srvHandler.getSocket());
            clients.remove(srvHandler.getPhone());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        logger.info("服务器已关闭。");
        //一些必要的清理工作
        if(null != servSocket){
            logger.info("服务器已关闭。");
            IOUtils.close(servSocket);
        }
    }

    public static void main(String[] args) {
        BioServer singleServer = null;
        try {
            BioServer.start();
            singleServer = BioServer.singleServer();
        } catch (IOException e) {
            if(null != singleServer){
                singleServer.setStartFlag(false);
            }
        }
    }

}
