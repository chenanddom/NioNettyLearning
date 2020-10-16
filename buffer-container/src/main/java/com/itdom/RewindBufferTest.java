package com.itdom;

import java.nio.ByteBuffer;

/**
 * ：重绕此缓冲区，将位置设置为0 并丢弃标记。
 * rewind（）方法的通俗解释就是“标记清除，位置position 值归0 , limit 不变” 。
 * rewind（） 方法没有设置限制，说明此方法可以结合自定义的limit 限制值。
 */
public class RewindBufferTest {
    public static void main(String[] args) {
        byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        System.out.println("capacity="+byteBuffer.capacity()+" limit="+byteBuffer.limit()+" position="+byteBuffer.position());
        byteBuffer.position(1);
        byteBuffer.limit(3);
        byteBuffer.mark();
        System.out.println("capacity="+byteBuffer.capacity()+" limit="+byteBuffer.limit()+" position="+byteBuffer.position());
        //这里重绕缓冲区
        /*
            public final Buffer rewind() {
        position = 0;
        mark = -1;
        return this;
    }
         */
        byteBuffer.rewind();
        System.out.println("capacity="+byteBuffer.capacity()+" limit="+byteBuffer.limit()+" position="+byteBuffer.position());
        byteBuffer.reset();
    }
}
