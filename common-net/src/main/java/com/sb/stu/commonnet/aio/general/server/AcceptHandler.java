package com.sb.stu.commonnet.aio.general.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncServerHandler> {

	private static final Logger logger = LoggerFactory.getLogger(AcceptHandler.class);

	@Override
	public void completed(AsynchronousSocketChannel channel,AsyncServerHandler serverHandler) {
		Server.clientCount++;
		logger.info("Server.clientCount={}", Server.clientCount);
		serverHandler.channel.accept(serverHandler, this);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		channel.read(buffer, buffer, new ReadHandler(channel));
	}

	@Override
	public void failed(Throwable exc, AsyncServerHandler serverHandler) {
		exc.printStackTrace();
		serverHandler.latch.countDown();
	}

}