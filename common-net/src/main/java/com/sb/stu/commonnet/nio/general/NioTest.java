package com.sb.stu.commonnet.nio.general;

import java.util.Scanner;

/**
 * 编  号：
 * 名  称：NioTest
 * 描  述：
 * 完成日期：2018/09/07 00:22
 *
 * @author：felix.shao
 */
public class NioTest {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        NioServer.start();
        Thread.sleep(100);
        NioClient.start();
        Scanner scanner = new Scanner(System.in);
        while(NioClient.sendMsg(scanner.nextLine())){

        };
    }

}
