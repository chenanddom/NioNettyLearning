package com.itdom;

import java.nio.CharBuffer;

public class FlipBufferTest {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(20);
        System.out.println("A position="+charBuffer.position()+ " limit="+charBuffer.limit());
        //一共写入14个字
        charBuffer.put("我是中国人我在中华人民共和国");
        System.out.println("B position="+charBuffer.position()+" limit="+charBuffer.limit());
        // 位置position还原成0
        charBuffer.position(0);
        System.out.println("C position="+charBuffer.position()+" limit="+charBuffer.limit());
        //这里将会把空格都一起读取出来
        for (int i = 0; i < charBuffer.limit() ; i++) {
            System.out.print(charBuffer.get());
        }
        System.out.println();

        System.out.println("D position="+charBuffer.position()+" limit="+charBuffer.limit());
        //还原缓冲区的状态
        charBuffer.clear();
        System.out.println("E position="+charBuffer.position()+" limit="+charBuffer.limit());
        charBuffer.put("我是中国人2");
        System.out.println("F position="+charBuffer.position()+" limit="+charBuffer.limit());
        //反转缓冲区
        charBuffer.flip();
        System.out.println("G position="+charBuffer.position()+" limit="+charBuffer.limit());
        for (int i = 0; i < charBuffer.limit() ; i++) {
            System.out.print(charBuffer.get());
        }


    }
}
