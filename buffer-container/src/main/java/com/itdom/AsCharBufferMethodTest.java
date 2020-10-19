package com.itdom;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * asCharBuffer（）方法的作用：创建此字节缓冲区的视图，作为char 缓冲区。新缓冲区的
 * 内容将从此缓冲区的当前位置开始。此缓冲区内容的更改在新缓冲区中是可见的，反之亦
 * 然；这两个缓冲区的位置、限制和标记值是相互独立的
 */
public class AsCharBufferMethodTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        byte[] byteArrayIn1 = "我是中国人 ".getBytes("UTF-16BE");
        byte[] byteArrayIn1 = "我是中国人 ".getBytes();
        System.out.println(Charset.defaultCharset().name());
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn1);
        System.out.println("bytebuffer="+byteBuffer.getClass().getName());
//        CharBuffer charBuffer = byteBuffer.asCharBuffer();
        //将bytebuffer使用utf-8进行编码。
        CharBuffer charBuffer = Charset.forName("UTF-8").decode(byteBuffer);
        System.out.println("charBuffer="+charBuffer.getClass().getName());
        System.out.println("bytebuffer.position="+byteBuffer.position()+" bytebuffer.capacity="+byteBuffer.capacity()+" bytebuffer.limit="+byteBuffer.limit());
        System.out.println("charBuffer.position="+charBuffer.position()+" charBuffer.capacity="+charBuffer.capacity()+" charBuffer.limit="+charBuffer.limit());
        System.out.println(charBuffer.capacity());
        charBuffer.position(0);
        /*
        II get （）方法使用的编码为UTF-16BE
        UTF - 8 与UTF-16BE 并不是同一种编码
        所以这时出现了乱码
         */
        for (int i = 0; i < charBuffer.limit() ; i++) {
            System.out.print(charBuffer.get()+" ");

        }
    }
}
