package com.java.stu.root.thread.thread01;

import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * 编  号：
 * 名  称：Thread01
 * 描  述：设计4个线程，其中两个线程每次对j增加1，另外两个线程对j每次减少1。写出程序
 * 完成日期：2018/8/4 15:36
 * @author：felix.shao
 */
public class Thread01 {

    private static volatile int num = 0;

    public synchronized static int increase(){
        num++;
        System.out.println("increase, num = " + num);
        return num;
    }

    public synchronized static int dec(){
        num--;
        System.out.println("dec, num = " + num);
        return num;
    }

    static class IncreaseThread extends Thread{

        private CountDownLatch countDownLatch;

        public IncreaseThread(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            Stream.iterate(0, i -> i + 1).limit(10).forEach(i -> increase());
            countDownLatch.countDown();
        }
    }

    static class DecThread extends Thread{

        private CountDownLatch countDownLatch;

        public DecThread(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            Stream.iterate(0, i -> i + 1).limit(10).forEach(i -> dec());
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(4);

        new IncreaseThread(countDownLatch).start();
        new IncreaseThread(countDownLatch).start();

        new DecThread(countDownLatch).start();
        new DecThread(countDownLatch).start();
        while (countDownLatch.getCount() > 0){}
        System.out.println("result:num = " + num);
    }

}
