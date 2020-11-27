package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileLock lock(long position, long siz巳， boolean shared）方法可以实现提前锁定，也就是当
 * 文件大小小于指定的position 时，是可以提前在position 位置处加锁的。
 */
public class LockInAdvanceTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("./LockInAdvanceTest.txt", "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        accessFileChannel.lock(6,2,true);
        accessFileChannel.write(ByteBuffer.wrap("1".getBytes()));
        accessFileChannel.write(ByteBuffer.wrap("2".getBytes()));
        accessFileChannel.write(ByteBuffer.wrap("3".getBytes()));
        accessFileChannel.write(ByteBuffer.wrap("4".getBytes()));
        accessFileChannel.write(ByteBuffer.wrap("5".getBytes()));
        accessFileChannel.write(ByteBuffer.wrap("6".getBytes()));
        accessFileChannel.write(ByteBuffer.wrap("7".getBytes()));
        /**
         * 执行程序得结果：
         * Exception in thread "main" java.io.IOException: 另一个程序已锁定文件的一部分，进程无法访问。
         * 	at sun.nio.ch.FileDispatcherImpl.write0(Native Method)
         * 	at sun.nio.ch.FileDispatcherImpl.write(FileDispatcherImpl.java:75)
         * 	at sun.nio.ch.IOUtil.writeFromNativeBuffer(IOUtil.java:93)
         * 	at sun.nio.ch.IOUtil.write(IOUtil.java:65)
         * 	at sun.nio.ch.FileChannelImpl.write(FileChannelImpl.java:211)
         * 	at com.itdom.LockInAdvanceTest.main(LockInAdvanceTest.java:25)
         */

    }
}
