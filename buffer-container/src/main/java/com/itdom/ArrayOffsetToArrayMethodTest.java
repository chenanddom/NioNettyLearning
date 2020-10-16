package com.itdom;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * 1. final in t arrayOffset（）方法的作用： 返回此缓冲区的底层实现数组中第一个缓冲区元素
 * 的偏移量，这个值在文档中标注为“可选操作” ， 也就是子类可以不处理这个值
 * <p>
 * 2. 如果List 中存储ByteB曲r 数据类型， 则可以使用List 中的toArray（） 方法转成ByteBuffer[]
 * 数组类型
 */
public class ArrayOffsetToArrayMethodTest {
    public static void main(String[] args) {
        byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        /**
         *     public final int arrayOffset() {
         *         if (hb == null)
         *             throw new UnsupportedOperationException();
         *         if (isReadOnly)
         *             throw new ReadOnlyBufferException();
         *         return offset;
         *     }
         */
        System.out.println(byteBuffer.arrayOffset());

        System.out.println("--------------------------------------------------------------------------------------------");

        final ByteBuffer byteBuffer1 = ByteBuffer.wrap(new byte[]{'a', 'b', 'c'});
        final ByteBuffer byteBuffer2 = ByteBuffer.wrap(new byte[]{'x', 'y', 'z'});
        final ByteBuffer byteBuffer3 = ByteBuffer.wrap(new byte[]{'1', '2', '3'});
        ArrayList<ByteBuffer> byteBuffers = new ArrayList<ByteBuffer>() {{
            add(byteBuffer1);
            add(byteBuffer2);
            add(byteBuffer3);
        }};
        ByteBuffer[] byteBufferArray = new ByteBuffer[byteBuffers.size()];
        byteBuffers.toArray(byteBufferArray);
        System.out.println(byteBufferArray.length);
        for (int i = 0; i < byteBufferArray.length; i++) {
            ByteBuffer eachBuffer = byteBufferArray[i];
            while (eachBuffer.hasRemaining()) {
                System.out.print((char) eachBuffer.get());
            }
            System.out.println();
        }
    }
}
