package com.itdom;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 取的字节数，可能为零。如果该通道已到达流的末尾，则返回一l 。
 * ReadableByteChannel 接口有以下两个特点：
 * 1 ）将通道当前位置中的字节序列读入1 个ByteBuffer 缓冲区中的remaining 空间中；
 * 2 ) read(ByteBuffer）方法是同步的。
 */
public class ReadMethodTest {

    public static void main(String[] args) throws IOException {
        /**
         * 1. 验证int read(ByteBuffer dst）方法返回值的意义
         * int read(ByteBuffer dst）方法返回int 类型，存在以下3 种值。
         * 1 ）正数：代表从通道的当前位置向ByteBuffer 缓冲区中读的字节个数。
         * 2 ) 0 ：代表从通道中没有读取任何的数据，也就是0 字节，有可能发生的情况就是缓
         * 冲区中没有remainging 剩余空间了。
         * 3 ) 1 ： 代表到达流的末端。
         */
        FileInputStream fileInputStream = new FileInputStream(new File("./test.txt"));
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        //取得5个字节
        int readLength = fileChannel.read(byteBuffer);
        //获取5个字节
        System.out.println(readLength);
        //再次执行的read()方法将没有剩余的空间，因为bytebuffer没有remaining剩余的空间。

        readLength = fileChannel.read(byteBuffer);
        System.out.println(readLength);
        //执行clear()使得缓冲区状态还原
        byteBuffer.clear();
        readLength = fileChannel.read(byteBuffer);

        System.out.println(readLength);

        byteBuffer.clear();
        // 这里没有任何内容需要读取的，直接的就是返回-1
        readLength = fileChannel.read(byteBuffer);
        System.out.println(readLength);


        //---------------验证int read(ByteBuffer dst）方法是从通道的当前位置开始读取的-------------------------------------
        FileInputStream fileInputStream1 = new FileInputStream(new File("./test.txt"));
        FileChannel fileChannel1 = fileInputStream1.getChannel();
        fileChannel1.position(2);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(5);
        readLength = fileChannel1.read(byteBuffer1);
        System.out.println(readLength);
        byte[] array = byteBuffer1.array();
        for (int i = 0; i < array.length; i++) {
            System.out.print((char) array[i]);
        }

        fileChannel.close();

        fileInputStream.close();
    }
}
