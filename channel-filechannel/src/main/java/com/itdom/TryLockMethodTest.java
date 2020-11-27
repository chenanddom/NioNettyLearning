package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * FileLock tryLock(long position, long size, boolean shared）方法的作用是试图获取对此通
 * 道的文件给定区域的锁定。此方法不会阻塞。无论是否已成功地获得请求区域上的锁定，调
 * 用总是立即返回。如果由于另一个程序保持着一个重叠锁定而无法获取锁定，则此方法返回
 * null 。如果由于任何其他原因而无法获取锁定，则抛出相应的异常。
 * 由position 和size 参数所指定的区域无须包含在实际的底层文件中，甚至无须与文件重
 * 叠。锁定区域的大小是固定的；如果某个已锁定区域最初包含整个文件，但文件因扩大而超
 * 出了该区域，则该锁定不覆盖此文件的新部分。如果期望文件大小扩大并且要求锁定整个文
 * 件，则应该锁定从零开始，到不小于期望最大文件大小为止的区域。零参数的tryLock（）方
 * 法只是锁定大小为Long.MAX_VALUE 的区域。
 * 某些操作系统不支持共享锁定， 在这种情况下，自动将对共享锁定的请求转换为对独
 * 占锁定的请求。可通过调用所得锁定对象的is Shared（）方法来测试新获取的锁定是共享的还
 * 是独占的。
 * 文件锁定以整个Java 虚拟机来保持。但它们不适用于控制同一虚拟机内多个线程对文
 * 件的访问。
 */
public class TryLockMethodTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./TryLockMethodTest.txt", "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        System.out.println("A begin");
        FileLock fileLock = accessFileChannel.tryLock(0, 5, false);
        System.out.println("A end 获得了锁 fileLock="+fileLock);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
