package com.sb.stu.commonnet.aio.general;

import com.sb.stu.commonnet.aio.general.client.Client;
import com.sb.stu.commonnet.aio.general.server.Server;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws Exception{
		Server.start();
		Thread.sleep(100);
		Client.start();
		Scanner scanner = new Scanner(System.in);
		while(Client.sendMsg(scanner.nextLine())){

		};
	}
}