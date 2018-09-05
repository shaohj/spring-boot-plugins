package com.sb.stu.commonnet.bio.general;

import com.alibaba.fastjson.util.IOUtils;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * 编  号：
 * 名  称：BioServerHandler
 * 描  述：
 * 完成日期：2018/09/04 20:52
 *
 * @author：felix.shao
 */
public class BioServerHandler implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BioServerHandler.class);

    /** 连接标志位，true:连接正常；alse:连接断开 */
    @Getter
    @Setter
    private boolean connFlag;

    @Getter
    private Socket socket;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String userName;

    public BioServerHandler(Socket socket) {
        this.socket = socket;
        // socket的流建立连接后不能被关闭，System.in的包装流不能被关闭
        BufferedReader br = null;
        try{
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    
            String message = "";
            logger.info("等待客户端录入签名数据,格式为phone|用户名");
            if(null != (message = br.readLine())){
                logger.info("收到客户端的签名数据：{}", message);
                String[] userData = message.split("[|]");
                this.setPhone(userData[0]);
                this.setUserName(userData[1]);
            }
            //建立连接成功
            connFlag = true;
        } catch (Exception e){
            logger.error("", e);
        }
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter out = null;
        try{
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);

            String msgData = "";
            while(connFlag){
                logger.info("BioServerHandler等待客户端请求...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                if(null != (msgData = br.readLine())){
                    logger.info("收到客户端请求，数据为{}", msgData);

                    String[] msgArr = msgData.split("[|]");
                    switch (msgArr[0]){
                        case "1" : doMessage(out, msgData); break;
                        case "2" : getAllConnInfo(out, msgData); break;
                        case "3" : closeConn(msgData); break;
                        default : break;
                    }
                }
            }
        } catch (Exception e){
            logger.error("处理客户端请求失败", e);
        } finally {
            logger.info("BioServerHandler run finally，关闭连接。");
            IOUtils.close(br);
            IOUtils.close(out);
        }
    }

    private void doMessage(PrintWriter out, String msgData){
        logger.info("处理文本通讯,msgData={}", msgData);
        try{
            String result = "数据处理完成："  + msgData;
            out.println(result);
        } catch (Exception e){
            logger.error("", e);
        }
    }

    private void getAllConnInfo(PrintWriter out, String msgData) {
        logger.info("处理获取所有的当前客户端连接信息,msgData={}", msgData);
        try{
            String result = BioServer.getAllCollInfo();
            out.println(result);
        }catch (Exception e){
            logger.error("", e);
        }
    }

    private void closeConn(String msgData){
        logger.info("与客户端断开连接,msgData={}", msgData);
        try{
            BioServer.closeConn(this);
        } catch (Exception e){
            logger.error("", e);
        } finally{
            IOUtils.close(socket);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        logger.info("服务器与客户端通讯已关闭。");
        //一些必要的清理工作
        if(null != socket){
            logger.info("服务器与客户端通讯已关闭。");
            IOUtils.close(socket);
        }
    }

}
