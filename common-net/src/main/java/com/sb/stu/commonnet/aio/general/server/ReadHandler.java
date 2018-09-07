package com.sb.stu.commonnet.aio.general.server;

import com.alibaba.fastjson.util.IOUtils;
import org.slf4j.*;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {

	private static final Logger logger = LoggerFactory.getLogger(ReadHandler.class);

	private AsynchronousSocketChannel channel;

	public ReadHandler(AsynchronousSocketChannel channel) {
			this.channel = channel;
	}

	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] message = new byte[attachment.remaining()];
		attachment.get(message);
		try {
			String expression = new String(message, "UTF-8");
			logger.info("expression = ", expression);
			String calrResult = "exec " + expression;
			//calrResult = Calculator.Instance.cal(expression).toString();
			doWrite(calrResult);
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
	}

	private void doWrite(String result) {
		byte[] bytes = result.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
		writeBuffer.put(bytes);
		writeBuffer.flip();
		channel.write(writeBuffer, writeBuffer,new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer buffer) {
				if (buffer.hasRemaining()){
					channel.write(buffer, buffer, this);
				} else{
					ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					channel.read(readBuffer, readBuffer, new ReadHandler(channel));
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				IOUtils.close(channel);
			}
		});
	}
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		IOUtils.close(channel);
	}
}