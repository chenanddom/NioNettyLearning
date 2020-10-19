package com.itdom;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 缓冲区根据类型进行索引
 * 视图缓冲区不是根据字节进行索引，而是根据其特定于类型的值的大小进
 * 行索引。
 */
public class BufferIndexByTypeTest {

    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println("A1="+byteBuffer.position());
        byteBuffer.putInt(123);
        System.out.println("A2="+byteBuffer.position());
        byteBuffer.putInt(456);
        System.out.println("A3="+byteBuffer.position());
        System.out.println("---------------视图缓冲区不是根据字节进行索引，而是根据其特定于类型的值的大小进行索引-------------------");
        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("B1="+intBuffer.position());
        intBuffer.put(456);
        System.out.println("B2="+intBuffer.position());
        intBuffer.put(789);
        System.out.println("B3="+intBuffer.position());
    }
}
