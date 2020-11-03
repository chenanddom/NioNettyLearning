package com.itdom;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * subSequence(int start, int end ）方法的作用：创建表示此缓冲区的指定序列、相对于
 * 当前位置的新字符缓冲区。新缓冲区将共享此缓冲区的内容，即如果此缓冲区的内容是可变
 * 的，则修改一个缓冲区将导致另一个缓冲区被修改。新缓冲区的容量将为此缓冲区的容量，
 * 其位置将为position() + start ，其限制将为position() + end 。当且仅当此缓冲区为直接缓冲区
 * 时，新缓冲区才是直接缓冲区。当且仅当此缓冲区为只读时，新缓冲区才是只读的。
 * 参数的解释如下。
 * 1 ) start ：子序列中第一个字符相对于当前位置的索引；必须为非负且不大于remaining（） 。
 * 2) end ：子序列中最后一个字符后面的字符相对于当前位置的索引；必须不小于start 且
 */
public class CharBufferSubSequenceMethodTest {

    public static void main(String[] args) throws IOException {
        CharBuffer charBuffer = CharBuffer.allocate(8);
        charBuffer.append("ab123456");
        charBuffer.position(2);
        charBuffer.put("cde");
        charBuffer.rewind();
        for (int i = 0; i <charBuffer.limit() ; i++) {
            System.out.print(charBuffer.get());
        }
        System.out.println();
        charBuffer.position(1);
        CharBuffer charBuffer1 = CharBuffer.allocate(4);
        System.out.println("A charBuffer1 position()="+charBuffer1.position());
        charBuffer.read(charBuffer1);
        System.out.println("B charBuffer1 position()="+charBuffer1.position());
        charBuffer1.rewind();
        for (int i = 0; i <charBuffer1.limit() ; i++) {
            System.out.print(charBuffer1.get());
        }
        System.out.println();
        charBuffer.position(2);
        CharBuffer charBuffer2 = charBuffer.subSequence(0, 2);
        System.out.println("C charBuffer2 position="+charBuffer2.position()+" capacity="+charBuffer2.capacity()+" limit="+charBuffer2.limit());

        for (int i = charBuffer2.position(); i <charBuffer2.limit() ; i++) {
            System.out.print(charBuffer2.get());
        }

    }

}
