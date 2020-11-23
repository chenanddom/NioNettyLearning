package com.itdom;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * FileLock lock(long position, long size, boolean shared）方法的作用是获取此通道的文件给
 * 定区域上的锁定。在可以锁定该区域之前、己关闭此通道之前或者已中断调用线程之前（ 以
 * 先到者为准），将阻塞此方法的调用。
 * 在此方法调用期间，如果另一个线程关闭了此通道， 则抛出AsynchronousCloseException
 * 异常。
 * 如果在等待获取锁定的同时中断了调用线程，则将状态设置为中断并抛出FileLock
 * InterruptionException 异常。如果调用此方法时己设置调用方的中断状态，则立即抛出该异
 * 常；不更改该线程的中断状态。
 * 由position 和size 参数所指定的区域无须包含在实际的底层文件中， 甚至元须与文件重
 * 叠。锁定区域的大小是固定的；如果某个己锁定区域最初包含整个文件，并且文件因扩大而
 * 超出了该区域，则该锁定不覆盖此文件的新部分。如果期望文件大小扩大并且要求锁定整个
 * 文件，则应该锁定的position 从零开始， s ize 传人大于或等于预计文件的最大值。零参数的
 * lock（） 方法只是锁定大小为Long.MAX_VALUE 的区域。
 * 文件锁定要么是独占的，要么是共享的。共享锁定可阻止其他并发运行的程序获取重
 * 叠的独占锁定，但是允许该程序获取重叠的共享锁定。独占锁定则阻止其他程序获取共享或
 * 独占类型的重叠锁定。
 * 某些操作系统不支持共享锁定，在这种情况下，自动将对共享锁定的请求转换为对独
 * 占锁定的请求。可通过调用所得锁定对象的is Shared（）方法来测试新获取的锁定是共享的还
 * 是独占的。
 * 文件锁定是以整个Java 虚拟机来保持的。但它们不适用于控制同一虚拟机内多个线程
 * 对文件的访问。
 */
public class PerformLockOperationTest3 {
    private static FileOutputStream fileOutputStream;
    private static FileChannel channel;

    public static void main(String[] args) throws IOException, InterruptedException {
    //2.验证AsynchronousCloseException 异常的发生
         fileOutputStream = new FileOutputStream("./PerformLockOperationTest.txt");
//        RandomAccessFile randomAccessFile = new RandomAccessFile("./PerformLockOperationTest.txt","rw");
//        FileChannel accessFileChannel = randomAccessFile.getChannel();

         channel = fileOutputStream.getChannel();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    channel.lock(1, 2, false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        Thread.sleep(1);
        thread1.start();


        Thread.sleep(1000);
        fileOutputStream.close();
        channel.close();
    }
}
