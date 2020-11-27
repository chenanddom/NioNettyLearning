package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *验证lock（）方法的参数position 和size 的含义
 * 参数position的作用是从哪个位置开始上锁，锁的范围由参数size 来决定。
 */
public class MeaningOfLockParametersTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./MeaningOfLockParametersTest.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        System.out.println("A position()="+fileChannel.position());
        fileChannel.lock(3,2,true);
        System.out.println("B position()="+fileChannel.position());
        fileChannel.write(ByteBuffer.wrap("1".getBytes()));
        System.out.println("C position()="+fileChannel.position());
        fileChannel.write(ByteBuffer.wrap("2".getBytes()));
        System.out.println("D position()="+fileChannel.position());
        fileChannel.write(ByteBuffer.wrap("3".getBytes()));
        System.out.println("E position()="+fileChannel.position());
        fileChannel.write(ByteBuffer.wrap("4".getBytes()));
        System.out.println("F position()="+fileChannel.position());
        /**
         * 运行上面得代码可以发现被锁定得数据无法进行更新：
         * 执行结果:
         * A position()=0
         * B position()=0
         * C position()=1
         * D position()=2
         * E position()=3
         * Exception in thread "main" java.io.IOException: 另一个程序已锁定文件的一部分，进程无法访问。
         * 	at sun.nio.ch.FileDispatcherImpl.write0(Native Method)
         * 	at sun.nio.ch.FileDispatcherImpl.write(FileDispatcherImpl.java:75)
         * 	at sun.nio.ch.IOUtil.writeFromNativeBuffer(IOUtil.java:93)
         * 	at sun.nio.ch.IOUtil.write(IOUtil.java:65)
         * 	at sun.nio.ch.FileChannelImpl.write(FileChannelImpl.java:211)
         * 	at com.itdom.MeaningOfLockParametersTest.main(MeaningOfLockParametersTest.java:27)
         */
    }
}
