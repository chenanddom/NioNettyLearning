package com.itdom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 验证int read(ByteBuffer dst）方法具有同步特
 */
public class ReadMethodSynchonousTest {

    private static FileInputStream fileInputStream;
    private static FileChannel fileChannel;

    public static void main(String[] args) throws IOException, InterruptedException {

        fileInputStream = new FileInputStream(new File("./read.txt"));
        fileChannel = fileInputStream.getChannel();
        new Thread() {
            @Override
            public void run() {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                    int readLenth = fileChannel.read(byteBuffer);
                    while (readLenth!=-1){
                        byte[] array = byteBuffer.array();
                        System.out.println(new String(array,0,readLenth));
                        byteBuffer.clear();
                        readLenth = fileChannel.read(byteBuffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(5);
                    int readLenth = fileChannel.read(byteBuffer);
                    while (readLenth!=-1){
                        byte[] array = byteBuffer.array();
                        System.out.println(new String(array,0,readLenth));
                        byteBuffer.clear();
                        readLenth = fileChannel.read(byteBuffer);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
        Thread.sleep(10000);
        fileChannel.close();
        fileInputStream.close();
    }
}
