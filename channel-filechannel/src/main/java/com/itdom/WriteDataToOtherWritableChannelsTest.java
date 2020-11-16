package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * long transferTo(position, count, WritableByteChannel <lest）方法的作用是将字节从此通道
 * 的文件传输到给定的可写入字节通道。transferTo（）方法的功能相当于write（）方法，只不过
 * 是将通道中的数据传输到另一个通道中，而不是缓冲区中。
 * 试图读取从此通道的文件中给定position 处开始的count 个字节，并将其写入目标通道
 * 的当前位置。此方法的调用不一定传输所有请求的字节， 是否传输取决于通道的性质和状
 * 态。如果此通道的文件从给定的position 处开始所包含的字节数小于count 个字节，或者如
 * 果目标通道是非阻塞的并且其输出缓冲区中的自由空间少于count 个字节，则所传输的字节
 * 数要小于请求的字节数。
 * 此方法不修改此通道的位置。如果给定的位置大于该文件的当前大小，则不传输任何
 * 字节，否则从目标通道的position 位置起始开始写入各字节，然后将该位置增加写人的字
 * 节数。
 * 与从此通道读取并将内容写人目标通道的简单循环语句相比，此方法可能高效得多。
 * 很多操作系统可将字节直接从文件系统缓存传输到目标通道，而无须实际复制各字节。
 * 该方法中的参数说明如下。
 * 1 ) position ：文件中的位置，从此位置开始传输，必须为非负数。
 * 2) count ：要传输的最大字节数；必须为非负数。
 * 3) <lest ：目标通道。
 * long transferTo(position, count, WritableByteChannel <lest）方法就是将数据写入WritableByteChannel
 * 通道中。
 */
public class WriteDataToOtherWritableChannelsTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFileA = new RandomAccessFile("./WriteDataToOtherWritableChannelsTest.txt", "rw");
        RandomAccessFile randomAccessFileB = new RandomAccessFile("./WriteDataToOtherWritableChannelsTest2.txt", "rw");

        FileChannel accessFileAChannelA = randomAccessFileA.getChannel();
        FileChannel accessFileAChannelB = randomAccessFileB.getChannel();

        accessFileAChannelB.position(2);

        accessFileAChannelA.transferTo(2, 3, accessFileAChannelB);

        accessFileAChannelA.close();
        accessFileAChannelB.close();
        randomAccessFileA.close();
        randomAccessFileB.close();


        //-------------------------------验证：如果count 的字节个数大于position 到size 的字节个数，则传输通道的sizeposition个字节数到de st 通道的当前位置----------------------------------------
        System.out.println("-------------------------------验证：如果count 的字节个数大于position 到size 的字节个数，则传输通道的sizeposition个字节数到de st 通道的当前位置----------------------------------------");
        /*
        在使用long transf1巳rTo(position, count, WritableByteChannel <lest）方法时，需要注意以下
        两种情况：
        1 ） 如果count 的字节个数大于position 到size 的字节个数，则传输通道的size-position
        个字节数到<lest 通道的当前位置；
        2 ） 如果count 的字节个数小于或等于position 到size 的字节个数， 则传输count 个字节
        数到de st 通道的当前位置。
         */
        RandomAccessFile randomAccessFileC = new RandomAccessFile("./WriteDataToOtherWritableChannelsTest3.txt","rw");
        RandomAccessFile randomAccessFileD = new RandomAccessFile("./WriteDataToOtherWritableChannelsTest4.txt","rw");

        FileChannel accessFileCChannelC = randomAccessFileC.getChannel();
        FileChannel accessFileCChannelD = randomAccessFileD.getChannel();

        System.out.println("A position="+accessFileCChannelC.position());
        accessFileCChannelC.transferTo(1,5,accessFileCChannelD);
        System.out.println("B position="+accessFileCChannelD.position());
        accessFileCChannelC.close();
        accessFileCChannelD.close();
        randomAccessFileC.close();
        randomAccessFileD.close();

    }
}
