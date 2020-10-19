package com.itdom;

import java.nio.ByteBuffer;

/**
 * allocateDirect( int capacity）方法的作用：分配新的直接字节缓冲区。新缓冲区的位置将
 * 为零，其界限将为其容量， 其标记是不确定的。无论它是否具有底层实现数组，其标记都是
 * 不确定的。
 * allocate(int capacity）方法的作用： 分配一个新的非直接字节缓冲区。新缓冲区的位置为
 * 零，其界限将为其容量，其标记是不确定的。它将具有一个底层实现数组，且其数组偏移量
 * 将为零。
 */
public class AllocateBufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(200);
        System.out.println("bytebuffer1 position="+byteBuffer.position()+" limit="+byteBuffer.limit());
        System.out.println("bytebuffer2 position="+byteBuffer2.position()+" limit="+byteBuffer2.limit());
        /**
         * bytebuffer1=java.nio.DirectByteBuffer[pos=0 lim=100 cap=100] isDirect=true
         * bytebuffer2=java.nio.HeapByteBuffer[pos=0 lim=200 cap=200] limit=false
         *使用allocateDirect（） 方法创建出来的缓冲区类型为DirectByteBuffer ， 使用a lloc ate （）方
         * 法创建出来的缓冲区类型为HeapByteBuffi巳r。
         * 使用allocateDirect（）方法创建ByteBuffer 缓冲区时， capacity 指的是字节的个数， 而创
         * 建IntBuffer 缓冲区时， capacity 指的是int 值的数目，如果要转换成字节，则capacity 的值
         * 要乘以4 ，来算出占用的总字节数。
         */
        System.out.println("bytebuffer1="+byteBuffer+" isDirect="+byteBuffer.isDirect());
        System.out.println("bytebuffer2="+byteBuffer2+" limit="+byteBuffer2.isDirect());
    }
}
