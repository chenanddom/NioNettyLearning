package com.itdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 验证int write(ByteBuffer src）方法真苟同步特性
 * 下面继续测试，使用多个线程同时对FileChannel 通道进行写入，并且int write(ByteBuffer
 * src）方法彼此之间出现同步的效果
 */
public class WriteMethodSynchronousTest {
    private static FileOutputStream fileOutputStream;
    private static FileChannel fileChannel;

    public static void main(String[] args) throws IOException, InterruptedException {
        fileOutputStream = new FileOutputStream(new File("./test3.txt"));
        fileChannel = fileOutputStream.getChannel();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    ByteBuffer byteBuffer = ByteBuffer.wrap("abcde\r\n".getBytes());
                    try {
                        fileChannel.write(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            new Thread() {
                @Override
                public void run() {
                    ByteBuffer byteBuffer = ByteBuffer.wrap("12345\r\n".getBytes());
                    try {
                        fileChannel.write(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
        Thread.sleep(3000);
        fileChannel.close();
        fileOutputStream.close();
    }
}
