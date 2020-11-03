package com.itdom;

import java.nio.CharBuffer;

/**
 *public static CharBuffer wrap(CharSequence csq, int start, int end）方法的作用：将字符序
 * 列包装到缓冲区中。新的只读缓冲区的内容将为给定字符序列的内容。缓冲区的容量将为
 * csq . length（），其位置将为s tart ，其限制将为end ， 其标记是未定义的。
 * 注意创建的这个该缓冲区为只读缓冲区并且缓冲区的容量为csq.length().

 */
public class CharBufferWrapMethodTest {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.wrap("abcdefg", 3, 5);
        System.out.println("capacity="+charBuffer.capacity()+"limit="+charBuffer.limit()+"position="+charBuffer.position());
        for (int i = 0; i < charBuffer.limit() ; i++) {
            System.out.print(charBuffer.get(i)+" ");
        }
        charBuffer.append("只读缓冲区不能添加数据");
    }

}
