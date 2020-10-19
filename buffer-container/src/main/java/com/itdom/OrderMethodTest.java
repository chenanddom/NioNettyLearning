package com.itdom;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * order（）方法与字节数据排列的顺序有关，因为不同的CPU 在读取字节时的顺序是不一
 * 样的，有的CPU 从高位开始读，而有的CPU 从低位开始读，当这两种CPU 传递数据时就
 * 要将字节排列的顺序进行统一，此时order(ByteOrder bo ）方法就有用武之地了，它的作用就
 * 是设置字节的排列顺序。
 * 什么是高位和低位呢？如果是16 位（ 双字节）的数据，如FFlA ，高位是FF ，低位是
 * lA 。如果是32 位的数据，如3F68415B ，高位字是3F 68 ，低位字是415B ，右边是低位，
 * 左边是高位。
 */
public class OrderMethodTest {
    public static void main(String[] args) {
        int value = 123456789;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        System.out.print(byteBuffer.order()+" ");
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.putInt(value);
        byte[] array = byteBuffer.array();
        for (int i = 0; i < array.length ; i++) {
            System.out.print(array[i]+" ");
        }

        System.out.println();
        byteBuffer = ByteBuffer.allocate(4);
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.putInt(value);
        array = byteBuffer.array();
        for (int i = 0; i < array.length ; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
        byteBuffer = ByteBuffer.allocate(4);
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        System.out.print(byteBuffer.order()+" ");
        byteBuffer.putInt(value);
       array = byteBuffer.array();
        for (int i = 0; i < array.length ; i++) {
            System.out.print(array[i]+" ");
        }

        System.out.println();

        ByteBuffer byteBuffer1 = ByteBuffer.allocate(8);
        byteBuffer1.order(ByteOrder.BIG_ENDIAN);
        byteBuffer1.putInt(123);
        byteBuffer1.putInt(567);
        byteBuffer1.flip();
        System.out.println(byteBuffer1.getInt());
        System.out.println(byteBuffer1.getInt());

        ByteBuffer byteBuffer2 = ByteBuffer.wrap(byteBuffer1.array());
        byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
        System.out.println(byteBuffer2.getInt());
        System.out.println(byteBuffer2.getInt());

    }

}
