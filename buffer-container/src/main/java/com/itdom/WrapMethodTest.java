package com.itdom;

import java.nio.ByteBuffer;

/**
 * wrap(byte[] array）方法的作用：将byte 数组包装到缓冲区中。新的缓冲区将由给定的
 * byte 数组支持，也就是说，缓冲区修改将导致数组修改，反之亦然。新缓冲区的capacity 和
 * limit 将为array.length ，其位置p osition 将为0 ，其标记mark 是不确定的。其底层实现数组
 * 将为给定数组，并且其arrayOffset 将为0 。
 *
 * 相关参数的解释如下。
 * 1 ) a叮叮：缓冲区中关联的字节数组。
 * 2) offset ：设置位置（ position ）值，该值必须为非负且不大于array.length 。
 * 3) length ：将新缓冲区的界限设置为offset + length ，该值必须为非负且不大于array.
 * length-offset 。
 * 注意： wrap(byte[] array, int offset, int length）方法并不具有sub String（）方法截取的作用，
 * 它的参数offset 只是设置缓冲区的position 值，而length 确定limit 值。
 *
 */
public class WrapMethodTest {

    public static void main(String[] args) {
        byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
        /**
         * 超出界限则会出现以下错误：
         * Exception in thread "main" java.nio.BufferUnderflowException
         * 	at java.nio.Buffer.nextGetIndex(Buffer.java:501)
         * 	at java.nio.HeapByteBuffer.get(HeapByteBuffer.java:138)
         * 	at com.itdom.WrapMethodTest.main(WrapMethodTest.java:28)
         */
        byteBuffer.get();

    }

}
