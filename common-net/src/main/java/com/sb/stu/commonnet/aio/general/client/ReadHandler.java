package com.sb.stu.commonnet.aio.general.client;

import com.alibaba.fastjson.util.IOUtils;
import org.slf4j.*;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.CountDownLatch;

public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {

	private static final Logger logger = LoggerFactory.getLogger(AsyncClientHandler.class);

	private AsynchronousSocketChannel clientChannel;

	private CountDownLatch latch;

	public ReadHandler(AsynchronousSocketChannel clientChannel,CountDownLatch latch) {
		this.clientChannel = clientChannel;
		this.latch = latch;
	}

	@Override
	public void completed(Integer result,ByteBuffer buffer) {
		buffer.flip();
		byte[] bytes = new byte[buffer.remaining()];
		buffer.get(bytes);
		String body;
		try {
			body = new String(bytes,"UTF-8");
			logger.info("" + body);
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
	}
	@Override
	public void failed(Throwable exc,ByteBuffer attachment) {
		logger.error("", exc);
		latch.countDown();
		IOUtils.close(clientChannel);
	}
}