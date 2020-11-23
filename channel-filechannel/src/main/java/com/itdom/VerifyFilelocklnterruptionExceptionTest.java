package com.itdom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 如果在等待获取锁定的同时中断了调用线程，则将状态设置为中断并抛出FileLock
 * InterruptionException 异常。如果调用FileLock lock(long position, long size, boolean shared)
 * 方法时己设置调用方的中断状态，则立即抛出该异常；不更改该线程的中断状态。
 */
public class VerifyFilelocklnterruptionExceptionTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream fileOutputStream = new FileOutputStream("./VerifyFilelocklnterruptionExceptionTest.txt");
        FileChannel channel = fileOutputStream.getChannel();
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000000; i++) {
                    System.out.println("i=" + (i + 1));
                }
                try {
                    channel.lock(1, 2, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        };
        thread.start();
        Thread.sleep(50);
        //终端线程
            thread.interrupt();
        Thread.sleep(30000);
        channel.close();
        fileOutputStream.close();

    }
}
