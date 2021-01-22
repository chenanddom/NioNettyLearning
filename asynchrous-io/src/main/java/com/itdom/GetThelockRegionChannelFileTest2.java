package com.itdom;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 获取通道文件给定区域的锁
 * public abstract Future<FileLock> lock(long position, long size, boolean shared）方法的作
 * 用是获取此通道文件给定区域的锁。此方法启动一个操作以获取此信道文件的给定区域的
 * 锁。该方法的行为与lock(long, long, boolean, Object, CompletionHandler）方法完全相同，不
 * 同之处在于，此方法不指定CompletionHandler 程序，而是返回一个表示待定结果的Future
 * 对象。Future 的get（）方法在成功完成时返回FileLock 。
 * 参数position 代表锁定区域的起始位置，必须是非负数。size 代表锁定区域的大小，必
 * 须是非负数，并且position + size 的结果必须是非负数。shared 值为true 代表请求的是共享
 * 锁，在这种情况下，此通道必须为读取（并可能写人）打开，如果请求排他锁，在这种情况
 * 下，此通道必须为写入而打开（并且可能读取） 。返回值代表待定结果的Future 对象。
 */
public class GetThelockRegionChannelFileTest2 {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Path path = Paths.get("./asynchrous-io/files/a.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        Future<FileLock> future = channel.lock(0, 3, false);
        FileLock lock = future.get();
        System.out.println("A get lock time=" + System.currentTimeMillis());
//        Thread.sleep(8000);
        lock.release();
        System.out.println("A release lock time=" + System.currentTimeMillis());
        channel.close();
    }
}
