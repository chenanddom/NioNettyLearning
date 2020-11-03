package com.itdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *long write(ByteBuffer[] srcs, int offset, int length）方法的作用是以指定缓冲区数组的offset
 * 下标开始，向后使用length 个字节缓冲区， 再将每个缓冲区的remaining 剩余字节子序列写
 * 入此通道的当前位置。
 * 1 ) offset ： 第一个缓冲区（要获取该缓冲区中的字节）在缓冲区数组中的偏移量；必须
 * 为非负数并且不能大于srcs.length 。
 * 2) length ： 要访问的最大缓冲区数；必须为非负数并且不能大于srcs.length - offset 。
 * long write(ByteBuffer[] srcs, int offset, int l ength） 方法实现的是Gath巳ringByteChannel 接
 * 口中的同名方法，而接口GatheringByteChannel 的父接口是WritableByteChannel ，说明接
 * 口GatheringByteChannel 也具有WritableByteChannel 接口的以下两个特性：
 * 1 ）将1 个ByteBuffer 缓冲区中的remaining 字节序列写入通道的当前位置；
 * 2 ) write(ByteBuffer）方法是同步的。
 */
public class PartialBatchWriteOperationsTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./PartialBatchWriteOperationsTest.txt"));
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer1 = ByteBuffer.wrap("abcde".getBytes());
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("12345".getBytes());
        ByteBuffer[] byteBuffers = {byteBuffer1, byteBuffer2};
        fileChannel.write(ByteBuffer.wrap("qqqqq".getBytes()));
        fileChannel.position(2);
        fileChannel.write(byteBuffers,0,2);
        //----------------------验证long write（时eBufferO s邸， int offset, int length）方法将印eBuffer 的remaining写入通道---------------------------------
        FileOutputStream fileOutputStream2 = new FileOutputStream(new File("./PartialBatchWriteOperationsTest2.txt"));
        FileChannel fileChannel2 = fileOutputStream2.getChannel();
        ByteBuffer byteBuffer3 = ByteBuffer.wrap("abcde".getBytes());

        ByteBuffer byteBuffer4 = ByteBuffer.wrap("12345".getBytes());
        byteBuffer4.position(1);
        byteBuffer4.limit(3);
        ByteBuffer byteBuffer5 = ByteBuffer.wrap("dlelfl".getBytes());
        byteBuffer5.position(2);
        byteBuffer5.limit(4);
        ByteBuffer[] byteBufferArray = new ByteBuffer[]{byteBuffer3,byteBuffer4,byteBuffer5};
        fileChannel2.write(byteBufferArray,1,2);
        fileChannel.close();
        fileOutputStream.close();
    }
}
