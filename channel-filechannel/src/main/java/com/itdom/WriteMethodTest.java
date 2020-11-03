package com.itdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 验证int write(ByteBuffer src ）万法是从通道的当前位置开始写入的
 *
 * 验证int write(ByteBuffer src ）万法将Byte Buffer 的remaining 写入通道
 */
public class WriteMethodTest {
    public static void main(String[] args) throws IOException {
        File file = new File("./test.txt");
        if (!file.exists()){
            file.createNewFile();
        }
        File file2 = new File("./test2.txt");
        if (!file2.exists()){
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        FileOutputStream fileOutputStream2 = new FileOutputStream(file2);

        FileChannel fileChannel = fileOutputStream.getChannel();
        FileChannel fileChannel2 = fileOutputStream2.getChannel();

        try {
            ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());

            System.out.println("A fileChannel.position()=" + fileChannel.position());
            System.out.println("write() 1返回值："+fileChannel.write(byteBuffer));
            System.out.println("B fileChannel.position()=" + fileChannel.position());
            fileChannel.position(2);
            byteBuffer.rewind();
            System.out.println("write() 2返回值："+fileChannel.write(byteBuffer));
            System.out.println("C fileChannel.position()=" + fileChannel.position());


            //-----------------验证int write(ByteBuffer src ）万法将Byte Buffer 的remaining 写入通道-------------------
            ByteBuffer byteBuffer1 = ByteBuffer.wrap("abcde".getBytes());
            ByteBuffer byteBuffer2 = ByteBuffer.wrap("12345".getBytes());
            fileChannel2.write(byteBuffer1);
            byteBuffer2.position(1);
            byteBuffer2.limit(3);
            fileChannel2.position(2);
            fileChannel2.write(byteBuffer2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileChannel.close();
        fileOutputStream.close();

    }
}
