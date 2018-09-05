package com.sb.stu.commonnet.bio.general;

import com.alibaba.fastjson.util.IOUtils;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

import static com.sb.stu.commonnet.bio.general.BioConstant.DEFAULT_SERVER_IP;

/**
 * 编  号：
 * 名  称：BioClient
 * 描  述：
 * 完成日期：2018/09/04 20:42
 *
 * @author：felix.shao
 */
public class BioClient {

    private static final Logger logger = LoggerFactory.getLogger(BioClient.class);

    /** 连接标志位，true:连接正常；alse:连接断开 */
    @Getter
    @Setter
    private boolean connFlag;

    /** socket */
    private Socket socket;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String userName;

    public BioClient(){
        int port = BioConstant.DEFAULT_SERVER_PORT;
        // socket的流建立连接后不能被关闭，System.in的包装流不能被关闭
        BufferedReader br = null;
        PrintWriter pw = null;
        logger.info("与服务器建立连接,port={},录入签名数据,格式为phone|用户名", port);
        try {
            br = new BufferedReader(new InputStreamReader(System.in));

            socket = new Socket(DEFAULT_SERVER_IP, port);
            pw = new PrintWriter(socket.getOutputStream(), true);

            String userData = br.readLine();
            pw.println(userData);

            //建立连接成功
            connFlag = true;
            logger.error("建立连接成功,port={}", port);
        } catch (IOException e) {
            logger.error("建立连接失败,port={}", port);
            logger.error("", e);
        }
    }

    public void sendMessage() {
        BufferedReader br = null;
        BufferedReader socketIn = null;
        PrintWriter socketPw = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketPw = new PrintWriter(socket.getOutputStream(), true);

            while(connFlag){
                logger.info("与服务器通讯,请录入消息体，格式为：类型|message\r\n" +
                        "-->支持的消息体格式如下：\r\n" +
                        "* 1|msg发送普通文本消息\r\n" +
                        "* 2获取当前所有连接信息\r\n" +
                        "* 3关闭连接");

                String msgData = br.readLine();
                String[] msgArr = msgData.split("[|]");
                /** 客户端接收服务端的信息合适的和方式为开一个子线程处理，此处忽略 */
                switch (msgArr[0]){
                    case "1" : sendMessage(socketIn, socketPw, msgData); break;
                    case "2" : getAllConnInfo(socketIn, socketPw, msgData); break;
                    case "3" : closeConn(socketPw, msgData); break;
                    default : break;
                }
            }
        } catch (Exception e){
            logger.error("与服务器通讯异常", e);
        } finally {
            logger.info("BioClient sendMessage finally，关闭连接。");
            // socket的流在此处或关闭处断开连接
            IOUtils.close(br);
            IOUtils.close(socket);
        }
    }

    private void sendMessage(BufferedReader socketIn, PrintWriter socketPw, String msgData){
        logger.info("文本通讯,msgData={}", msgData);
        try{
            // socket的流通讯时不能被关闭
            socketPw.println(msgData);
            logger.info("接收到服务器返回数据：{}", socketIn.readLine());
        } catch (Exception e){
            logger.error("", e);
        }
    }

    private void getAllConnInfo(BufferedReader socketIn, PrintWriter socketPw, String msgData){
        logger.info("获取所有的当前客户端连接信息,msgData={}", msgData);
        try{
            socketPw.println(msgData);
            logger.info("接收到服务器返回数据：{}", socketIn.readLine());
        } catch (Exception e){
            logger.error("", e);
        }
    }

    private void closeConn(PrintWriter socketPw, String msgData){
        logger.info("与服务器断开连接,msgData={}", msgData);
        try{
            // socket的流通讯时不能被关闭
            socketPw.println(msgData);
        } catch (Exception e){
            logger.error("", e);
        } finally{
            connFlag = false;
            IOUtils.close(socket);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        logger.info("客户端与服务器通讯已关闭。");
        super.finalize();
        //一些必要的清理工作
        if(null != socket){
            logger.info("客户端与服务器通讯已关闭。");
            IOUtils.close(socket);
        }
    }

    public static void main(String[] args) {
        /** 13300000001|张三
         *
         */
        BioClient client1 = new BioClient();
        client1.sendMessage();
    }

}
