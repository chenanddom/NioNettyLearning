package com.itdom;

import java.nio.ByteBuffer;

/**
 * compact（）方法的作用： 压缩此缓冲区（可选操作），将缓冲区的当前位置和限制之间的字
 * 节（如果有）复制到缓冲区的开始处， 即将索引p = position（）处的字节复制到索引0 处，将索
 * 引p+l 处的字节复制到索引1 处， 依此
 * 类推，直到将索引limit() - 1 处的字节复
 * 制到索引n = limit（）一l -p 处。然后，将
 * 缓冲区的位置设置为n+ l ，并将其限制
 * 设置为其容量。如果已定义了标记，则
 * 丢弃它。将缓冲区的位置设置为复制的
 * 字节数，而不是0 ，以便调用此方法后
 * 可以紧接着调用另一个相对put 方法。
 */
public class CompactMethodTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{1, 2, 3, 4, 5, 6});
        System.out.println("A capacity="+byteBuffer.capacity()+" position="+byteBuffer.position()+" limit="+byteBuffer.limit());
        System.out.println("1 getValue="+byteBuffer.get());
        System.out.println("B capacity="+byteBuffer.capacity()+" position="+byteBuffer.position()+" limit="+byteBuffer.limit());
        System.out.println("2 getValue="+byteBuffer.get());
        System.out.println("C capacity="+byteBuffer.capacity()+" position="+byteBuffer.position()+" limit="+byteBuffer.limit());
        byteBuffer.compact();
        System.out.println("bytebuffer.compact()");
        System.out.println("D capacity="+byteBuffer.capacity()+" position="+byteBuffer.position()+" limit="+byteBuffer.limit());
        byte[] array = byteBuffer.array();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }

}
