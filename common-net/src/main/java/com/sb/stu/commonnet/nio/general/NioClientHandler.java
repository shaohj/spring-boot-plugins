package com.sb.stu.commonnet.nio.general;

import com.alibaba.fastjson.util.IOUtils;
import org.slf4j.*;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

/**
 * 编  号：
 * 名  称：NioClientHandler
 * 描  述：
 * 完成日期：2018/09/06 15:07
 *
 * @author：felix.shao
 */
public class NioClientHandler implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NioClientHandler.class);

    /** 服务器主机,即服务器ip值 */
    private String host;

    /** 服务器端口 */
    private int port;

    /** 选择器 */
    private Selector selector;

    private SocketChannel socketChannel;

    /** 启动标志位，true为已启动，false为未启动 */
    private volatile boolean started;

    public NioClientHandler(String ip,int port) {
        this.host = ip;
        this.port = port;
        try{
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            started = true;
        }catch(IOException e){
            logger.error("", e);
        }
    }

    public void stop(){
        started = false;
    }

    @Override
    public void run() {
        try{
            doConnect();
        }catch(IOException e){
            logger.error("", e);
            System.exit(1);
        }

        while(started){
            try{
                int num = selector.select(1000);
                if(num > 0){
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> it = keys.iterator();
                    SelectionKey key = null;
                    while(it.hasNext()){
                        key = it.next();
                        it.remove();
                        handleInput(key);
                    }
                }
            }catch(Exception e){
                logger.error("NioClientHandler run error", e);
                System.exit(1);
            }
        }
        if(selector != null){
            IOUtils.close(selector);
        }
    }
    private void handleInput(SelectionKey key) throws IOException{
        if(key.isValid()){
            try{
                SocketChannel sc = (SocketChannel) key.channel();
                if(key.isConnectable()){
                    if(!sc.finishConnect()){
                        System.exit(1);
                    }
                } else if(key.isReadable()){
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(buffer);
                    if(readBytes > 0){
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String result = new String(bytes,"UTF-8");
                        logger.info("接收服务器数据：{}", result);
                    }
                    else if(readBytes<0){
                        key.cancel();
                        sc.close();
                    }
                }
            }catch(Exception e){
                if(key != null){
                    key.cancel();
                    IOUtils.close(key.channel());
                }
            }
        }
    }

    private void doConnect() throws IOException{
        logger.info("请求连接服务器，ip={},port={}", host, port);
        /**
         * true if a connection was established,
         * false if this channel is in non-blocking mode
         * 连接失败，抛出异常
         */
        boolean isConn = socketChannel.connect(new InetSocketAddress(host, port));
        if(!isConn){
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            logger.info("非阻塞式连接服务器成功，ip={},port={}", host, port);
        }
    }

    public void sendMsg(String msg) throws Exception{
        socketChannel.register(selector, SelectionKey.OP_READ);
        NioUtils.doWrite(socketChannel, msg);
    }

}
