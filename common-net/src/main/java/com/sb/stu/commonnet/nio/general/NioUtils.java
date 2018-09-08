package com.sb.stu.commonnet.nio.general;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 编  号：
 * 名  称：NioUtil
 * 描  述：
 * 完成日期：2018/09/07 19:40
 *
 * @author：felix.shao
 */
public class NioUtils {

    public static void doWrite(SocketChannel channel, String requestMsg) throws IOException {
        byte[] bytes = requestMsg.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }

}
