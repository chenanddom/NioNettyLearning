package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * boolean overlaps(long position, long size）方法的作用：判断此锁定是否与给定的锁定区
 * 域重叠。返回值是boolean 类型，也就是当且仅当此锁定与给定的锁定区域至少重叠一个字
 * 节时，才返回true 。
 */
public class OverlapsMethodTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./OverlapsMethodTest.txt", "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        FileLock fileLock = accessFileChannel.lock(1, 10, true);
        System.out.println(fileLock.overlaps(5, 10));
        /**
         * 程序执行的结果:
         * true
         */
        System.out.println(fileLock.overlaps(11, 100));
    }
}
