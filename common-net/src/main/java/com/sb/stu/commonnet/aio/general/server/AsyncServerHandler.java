package com.sb.stu.commonnet.aio.general.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncServerHandler implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(AsyncServerHandler.class);

	public CountDownLatch latch;

	public AsynchronousServerSocketChannel channel;

	public AsyncServerHandler(int port) {
		try {
			channel = AsynchronousServerSocketChannel.open();
			channel.bind(new InetSocketAddress(port));
			logger.info("AsyncServerHandler init port={}", port);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);
		channel.accept(this,new AcceptHandler());
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}
}