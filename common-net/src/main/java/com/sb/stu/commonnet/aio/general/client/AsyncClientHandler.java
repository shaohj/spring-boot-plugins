package com.sb.stu.commonnet.aio.general.client;

import com.alibaba.fastjson.util.IOUtils;
import org.slf4j.*;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.concurrent.CountDownLatch;

public class AsyncClientHandler implements CompletionHandler<Void, AsyncClientHandler>, Runnable {

	private static final Logger logger = LoggerFactory.getLogger(AsyncClientHandler.class);

	private AsynchronousSocketChannel clientChannel;

	private String host;

	private int port;

	private CountDownLatch latch;

	public AsyncClientHandler(String host, int port) {
		this.host = host;
		this.port = port;
		try {
			clientChannel = AsynchronousSocketChannel.open();
		} catch (IOException e) {
			logger.error("", e);
		}
	}
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		clientChannel.connect(new InetSocketAddress(host, port), this, this);
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error("", e);
		}
		IOUtils.close(clientChannel);
	}

	@Override
	public void completed(Void result, AsyncClientHandler attachment) {
		logger.info("AsyncClientHandler completed");
	}

	@Override
	public void failed(Throwable exc, AsyncClientHandler attachment) {
		logger.info("AsyncClientHandler failed");
		logger.error("", exc);
		latch.countDown();
		IOUtils.close(clientChannel);
	}

	public void sendMsg(String msg){
		byte[] req = msg.getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		clientChannel.write(writeBuffer, writeBuffer,new WriteHandler(clientChannel, latch));
	}
}
