package com.itdom;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * position(long newPosition） 方法的作用是设置此通道的文件位置。将该位置设置为大于
 * 文件当前大小的值是合法的，但这不会更改文件的大小，稍后试图在这样的位置读取字节将
 * 立即返回己到达文件末尾的指示， 稍后试图在这种位置写人字节将导致文件扩大，以容纳新
 * 的字节， 在以前文件末尾和新写入字节之间的字节值是未指定的。
 */
public class SetPositionAndGetSizeTest {

    public static void main(String[] args) throws IOException {

        ByteBuffer byteBuffer1 = ByteBuffer.wrap("abcd".getBytes());
        ByteBuffer byteBuffer2 = ByteBuffer.wrap("cde".getBytes());

        FileOutputStream fileOutputStream = new FileOutputStream(new File("./SetPositionAndGetSizeTest.txt"));

        FileChannel channel = fileOutputStream.getChannel();
        System.out.println("A position="+channel.position()+" Size="+channel.size());
        channel.write(byteBuffer1);
        System.out.println("B position="+channel.position()+" Size="+channel.size());
        channel.position(2);
        System.out.println("C position="+channel.position()+" Size="+channel.size());
        channel.write(byteBuffer2);
        System.out.println("D position="+channel.position()+" Size="+channel.size());
        channel.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
