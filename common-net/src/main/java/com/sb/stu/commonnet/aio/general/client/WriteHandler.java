package com.sb.stu.commonnet.aio.general.client;

import com.alibaba.fastjson.util.IOUtils;
import org.slf4j.*;

import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.CountDownLatch;

public class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {

	private static final Logger logger = LoggerFactory.getLogger(AsyncClientHandler.class);

	private AsynchronousSocketChannel clientChannel;

	private CountDownLatch latch;

	public WriteHandler(AsynchronousSocketChannel clientChannel,CountDownLatch latch) {
		this.clientChannel = clientChannel;
		this.latch = latch;
	}

	@Override
	public void completed(Integer result, ByteBuffer buffer) {
		if (buffer.hasRemaining()) {
			clientChannel.write(buffer, buffer, this);
		}
		else {
			ByteBuffer readBuffer = ByteBuffer.allocate(1024);
			clientChannel.read(readBuffer,readBuffer,new ReadHandler(clientChannel, latch));
		}
	}
	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		logger.error("", exc);
		latch.countDown();
		IOUtils.close(clientChannel);
	}
}