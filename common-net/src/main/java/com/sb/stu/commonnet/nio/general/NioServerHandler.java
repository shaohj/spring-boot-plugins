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
 * 名  称：NioServerHandler
 * 描  述：
 * 完成日期：2018/9/7 16:06
 * @author：felix.shao
 */
public class NioServerHandler implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NioServerHandler.class);

    /** 选择器 */
    private Selector selector;

    /** 服务器套接字实例 */
    private ServerSocketChannel serverChannel;

    /** 注册ServerSocketChannel后的选择键 */
    private SelectionKey selectionKey;

    /** 启动标志位，true为已启动，false为未启动 */
    private volatile boolean started;

    public NioServerHandler(int port) {
        try{
            //获得选择器实例
            selector = Selector.open();
            //获得服务器套接字实例
            serverChannel = ServerSocketChannel.open();
            //绑定端口号
            serverChannel.socket().bind(new InetSocketAddress(port),1024);
            //设置为非阻塞
            serverChannel.configureBlocking(false);
            //将ServerSocketChannel注册到选择器，指定其行为为"等待接受连接"
            selectionKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            started = true;
            logger.info("init NioServerHandler success");
        } catch(IOException e){
            logger.error("", e);
        }
    }

    @Override
    public void run() {
        while(started){
            try{
                int num = selector.select(1000);
                if(num > 0){
                    Iterator<SelectionKey> itor = selector.selectedKeys().iterator();
                    SelectionKey key = null;
                    while(itor.hasNext()) {
                        key = itor.next();
                        itor.remove();
                        handleInput(key);
                    }
                }
            }catch(Exception e){
                logger.error("NioServerHandler run error", e);
                System.exit(1);
            }
        }
        if(selector != null){
            IOUtils.close(selector);
        }
    }
    private void handleInput(SelectionKey key){
        if(key.isValid()){
            logger.info("handleInput,key={}", key.toString());
            try{
                if(key.isAcceptable()){
                    logger.info("有客户端新连接产生了");
                    ServerSocketChannel srvSockChan = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = srvSockChan.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if(key.isReadable()){
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(buffer);
                    if(readBytes > 0){
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        String userData = new String(bytes,"UTF-8");


                        String result = "服务器处理数据：" + userData;
                        logger.info(result);
                        NioUtils.doWrite(sc, result);
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

}
