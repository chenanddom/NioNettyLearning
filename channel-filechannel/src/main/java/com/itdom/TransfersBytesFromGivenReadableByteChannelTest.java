package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * long transferFrom(ReadableByteChannel src, position, count）方法的作用是将字节从给定
 * 的可读取字节通道传输到此通道的文件中。transferFrom（）方法的功能相当于read （）方法，
 * 只不过是将通道中的数据传输到另一个通道中，而不是缓冲区中。
 * 试着从源通道中最多读取count 个字节，并将其写入到此通道的文件中从给定pos ition
 * 处开始的位置。此方法的调用不一定传输所有请求的字节； 是否传输取决于通道的性质和状
 * 态。如果源通道的剩余空间小于count 个字节，或者如果源通道是非阻塞的并且其输入缓冲
 * 区中直接可用的空间小于count 个字节， 则所传输的字节数要小于请求的字节数。
 * 此方法不修改此通道的位置。如果给定的位置大于该文件的当前大小，则不传输任何
 * 字节。从源通道中的当前位置开始读取各字节写入到当前通道，然后将src 通道的位置增加
 * 读取的字节数。
 * 与从源通道读取并将内容写入此通道的简单循环语句相比，此方法可能高效得多。很
 * 多操作系统可将字节直接从源通道传输到文件系统缓存，而无须实际复制各字节。
 * 该方法的参数说明如下。
 * 1 ) src ：源通道。
 * 2) position ：文件中的位置，从此位置开始传输；必须为非负数。
 * 3) count ： 要传输的最大字节数；必须为非负数。
 * 注意，参数position 是指当前通道的位置，而不是指src 源通道的位置。
 */
public class TransfersBytesFromGivenReadableByteChannelTest {
    public static void main(String[] args) throws IOException {
        //------------------------1 . 如果给定的位置大于该文件的当前大小，则不传输任何字节-------------------------------------------------------
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("./TransfersBytesFromGivenReadableByteChannelTest.txt", "rw");
        RandomAccessFile randomAccessFile2 = new RandomAccessFile("./TransfersBytesFromGivenReadableByteChannelTest2.txt", "rw");

        FileChannel randomAccessFile1Channel1 = randomAccessFile1.getChannel();
        FileChannel randomAccessFile1Channel2 = randomAccessFile2.getChannel();

        randomAccessFile1Channel2.position(4);

        long readLength = randomAccessFile1Channel1.transferFrom(randomAccessFile1Channel2, 3, 2);
        System.out.println(readLength);

        randomAccessFile1Channel1.close();
        randomAccessFile1Channel2.close();
        randomAccessFile1.close();
        randomAccessFile2.close();



        //------------------------验证：如果count 的字节个数大于src.remaining ，则通道的src.remaining 字节数传输到当前通道的position 位置--------------------------

        /**
         * 在使用long transferFrom(ReadableByteChannel src, position, count） 方法时， 需要注意以
         * 下两种情况：
         * 1 ）如果count 的字节个数大于src.remaining ，则通道的src.remaining 字节数传输到当
         * 前通道的position 位置；
         * 2 ）如果count 的字节个数小于或等于src.remaining ， 则count 个字节传输到当前通道的
         * position 位置。
         */
        RandomAccessFile randomAccessFile3 = new RandomAccessFile("./TransfersBytesFromGivenReadableByteChannelTest3.txt", "rw");
        RandomAccessFile randomAccessFile4 = new RandomAccessFile("./TransfersBytesFromGivenReadableByteChannelTest4.txt", "rw");

        FileChannel fileChannel3 = randomAccessFile3.getChannel();
        FileChannel fileChannel4 = randomAccessFile4.getChannel();

        fileChannel4.position(2);

        long readLength2 = fileChannel3.transferFrom(fileChannel4, 1, 200);
        System.out.println(readLength2);

         fileChannel3.close();
         fileChannel4.close();

         randomAccessFile3.close();
         randomAccessFile4.close();

         //验证： 如果count 的字节个数小于或等于S 「c.remaining ，则count 个字节传输到当前通道的posit i on 位置
        System.out.println("----------------验证： 如果count 的字节个数小于或等于S 「c.remaining ，则count 个字节传输到当前通道的posit i on 位置----------------------------");

        RandomAccessFile randomAccessFile5 = new RandomAccessFile("./TransfersBytesFromGivenReadableByteChannelTest5.txt", "rw");
        RandomAccessFile randomAccessFile6 = new RandomAccessFile("./TransfersBytesFromGivenReadableByteChannelTest6.txt", "rw");

        FileChannel fileChannel5 = randomAccessFile5.getChannel();
        FileChannel fileChannel6 = randomAccessFile6.getChannel();

        fileChannel5.position(2);

        long readLength3 = fileChannel5.transferFrom(fileChannel6, 1, 2);
        System.out.println(readLength3);

        fileChannel5.close();
        fileChannel6.close();

        randomAccessFile5.close();
        randomAccessFile6.close();




    }
}
