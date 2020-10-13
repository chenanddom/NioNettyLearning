package com.itdom;

import java.nio.*;

/**
 * 0=<mark=<position=<limit=<capacity
 */
public class CapacityPositionLimitMarkMethodTest {

    public static void main(String[] args) {

        byte[] byteArray = new byte[]{1,2,3};
        short[] shortArray = new short[]{1,2,3,4};
        int[] intArray = new int[]{1,2,3,4,5};
        long[] longArray = new long[]{1,2,3,4,5,6};
        float[] floatArray = new float[]{1,2,3,4,5,6,7};
        double[] doubleArray = new double[]{1,2,3,4,5,6,7,8};
        char[] charArray = new char[]{'a','b','c','d'};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        IntBuffer intBuffer = IntBuffer.wrap(intArray);
        LongBuffer longBuffer = LongBuffer.wrap(longArray);
        FloatBuffer floatBuffer = FloatBuffer.wrap(floatArray);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubleArray);
        CharBuffer charBuffer = CharBuffer.wrap(charArray);

        System.out.println("byteBuffer="+byteBuffer.getClass().getName());
        System.out.println("intBuffer="+intBuffer.getClass().getName());
        System.out.println("longBuffer="+longBuffer.getClass().getName());
        System.out.println("floatBuffer="+floatBuffer.getClass().getName());
        System.out.println("doubleBuffer="+doubleBuffer.getClass().getName());
        System.out.println("charBuffer="+charBuffer.getClass().getName());
        System.out.println();

        System.out.println("byteBuffer.capacity="+byteBuffer.capacity());
        System.out.println("intBuffer.capacity="+intBuffer.capacity());
        System.out.println("longBuffer.capacity="+longBuffer.capacity());
        System.out.println("floatBuffer.capacity="+floatBuffer.capacity());
        System.out.println("doubleBuffer.capacity="+doubleBuffer.capacity());
        System.out.println("charBuffer.capacity="+charBuffer.capacity());

        //remaining()的内部是 limit - position;
        System.out.println(charBuffer.remaining());

        //mark()在次缓冲区的位置设置标记，
        // 如果将position或者limit调整小于该mark标记为，则该mark将会被丢弃。

        System.out.println("byteBuffer.position():"+byteBuffer.position());

        byteBuffer.position(1);
        byteBuffer.mark();//在1的位置设置mark标识
        System.out.println("byteBuffer.position():"+byteBuffer.position());

        byteBuffer.position(2);
        System.out.println("byteBuffer.position():"+byteBuffer.position());

        //重置标志位
        byteBuffer.reset();
        System.out.println("byteBuffer.position():"+byteBuffer.position());

    }

}
