package com.itdom;

import java.nio.ByteBuffer;

/**
 * slice（）方法的作用：创建新的字节缓冲区，其内容是此缓冲区内容的共享子序列。新
 * 缓冲区的内容将从此缓冲区的当前位置开始。此缓冲区内容的更改在新缓冲区中是可见的，
 * 反之亦然；这两个缓冲区的位置、限制和标记值是相互独立的。新缓冲区的位置将为0 ，其
 * 容量和限制将为此缓冲区中所剩余的字节数量，其标记是不确定的。当且仅当此缓冲区为
 * 直接缓冲区时，新缓冲区才是直接缓冲区。当且仅当此缓冲区为只读时，新缓冲区才是只
 * 读的。
 */
public class SliceMethodTest {
    public static void main(String[] args) {
        byte[] byteArrayIn1 = {1,2,3,4,5,6,7,8,9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn1);
        byteBuffer.position(5);
        ByteBuffer byteBuffer2 = byteBuffer.slice();
        System.out.println("bytebuffer1.position="+byteBuffer.position()+" bytebuffer1.capacity="+byteBuffer.capacity()+" bytebuffer1.limit="+byteBuffer.limit());

        System.out.println("bytebuffer2.position="+byteBuffer2.position()+" bytebuffer2.capacity="+byteBuffer2.capacity()+" bytebuffer2.limit="+byteBuffer2.limit());
        byteBuffer2.put(0,(byte)111);
        byte[] byteArray1 = byteBuffer.array();
        byte[] byteArray2 = byteBuffer2.array();
        for (int i = 0; i < byteArray1.length; i++) {
            System.out.print(byteArray1[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < byteArray2.length; i++) {
            System.out.print(byteArray2[i]+" ");
        }
        System.out.println();
        System.out.println("--------------------在使用s lice （）方法后，再调用arrayOffSet（） 方法时，会出现返回值为非0 的情况-----------------------------");
        System.out.println(byteBuffer2.arrayOffset());
    }
}
