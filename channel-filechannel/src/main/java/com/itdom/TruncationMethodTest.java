package com.itdom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *truncate(long size）方法的作用是将此通道的文件截取为给定大小。如果给定大小小于该
 * 文件的当前大小，则截取该文件，丢弃文件新末尾后面的所有字节。如果给定大小大于或等
 * 于该文件的当前大小， 则不修改文件。无论是哪种情况，如果此通道的文件位置大于给定大
 * 小，则将位置设置为该大小。
 */
public class TruncationMethodTest {
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap("12345678".getBytes());
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./TruncationMethodTest.txt"));

        FileChannel fileChannel = fileOutputStream.getChannel();

        fileChannel.write(byteBuffer);
        System.out.println("A Size="+fileChannel.size()+" position="+fileChannel.position());
        fileChannel.truncate(3);
        System.out.println("B Size="+fileChannel.size()+" position="+fileChannel.position());

        fileChannel.close();
        fileOutputStream.flush();
        fileChannel.close();


    }
}
