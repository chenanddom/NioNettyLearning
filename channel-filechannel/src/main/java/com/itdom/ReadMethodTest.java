package com.itdom;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 取的字节数，可能为零。如果该通道已到达流的末尾，则返回一l 。
 * ReadableByteChannel 接口有以下两个特点：
 * 1 ）将通道当前位置中的字节序列读入1 个ByteBuffer 缓冲区中的remaining 空间中；
 * 2 ) read(ByteBuffer）方法是同步的。
 */
public class ReadMethodTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        /**
         * 1. 验证int read(ByteBuffer dst）方法返回值的意义
         * int read(ByteBuffer dst）方法返回int 类型，存在以下3 种值。
         * 1 ）正数：代表从通道的当前位置向ByteBuffer 缓冲区中读的字节个数。
         * 2 ) 0 ：代表从通道中没有读取任何的数据，也就是0 字节，有可能发生的情况就是缓
         * 冲区中没有remainging 剩余空间了。
         * 3 ) 1 ： 代表到达流的末端。
         */
        FileInputStream fileInputStream = new FileInputStream(new File("./test.txt"));
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        //取得5个字节
        int readLength = fileChannel.read(byteBuffer);
        //获取5个字节
        System.out.println(readLength);
        //再次执行的read()方法将没有剩余的空间，因为bytebuffer没有remaining剩余的空间。

        readLength = fileChannel.read(byteBuffer);
        System.out.println(readLength);
        //执行clear()使得缓冲区状态还原
        byteBuffer.clear();
        readLength = fileChannel.read(byteBuffer);

        System.out.println(readLength);

        byteBuffer.clear();
        // 这里没有任何内容需要读取的，直接的就是返回-1
        readLength = fileChannel.read(byteBuffer);
        System.out.println(readLength);

        fileChannel.close();

        fileInputStream.close();

        //---------------验证int read(ByteBuffer dst）方法是从通道的当前位置开始读取的-------------------------------------
        FileInputStream fileInputStream1 = new FileInputStream(new File("./test.txt"));
        FileChannel fileChannel1 = fileInputStream1.getChannel();
        fileChannel1.position(2);
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(5);
        readLength = fileChannel1.read(byteBuffer1);
        System.out.println(readLength);
        byte[] array = byteBuffer1.array();
        for (int i = 0; i < array.length; i++) {
            System.out.print((char) array[i]);
        }

        fileChannel1.close();

        fileInputStream1.close();
        System.out.println();

        System.out.println("--------------验证long read(ByteBuffer[] dsts, int offset, int length ）方法将字节放入ByteBuffer当前位置--------------");


        //验证long read(ByteBuffer[] dsts, int offset, int length ）方法将字节放入ByteBuffer当前位置
        FileInputStream fileInputStream2 = new FileInputStream(new File("./ReadMethodTest.txt"));
        FileChannel fileChannel2 = fileInputStream2.getChannel();

        ByteBuffer byteBuffer2 = ByteBuffer.allocate(8);
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(8);
        byteBuffer2.position(3);
        byteBuffer3.position(4);

        ByteBuffer[] byteBufferArray = {byteBuffer2, byteBuffer3};
        fileChannel2.read(byteBufferArray);
        for (int i = 0; i < byteBufferArray.length; i++) {
            ByteBuffer buffer = byteBufferArray[i];
            byte[] bytes = buffer.array();
            for (int i1 = 0; i1 < bytes.length; i1++) {
                System.out.print((char) bytes[i1]);
            }
            System.out.println();
        }
        fileChannel2.close();

        fileInputStream2.close();

        System.out.println();
        System.out.println("------------------------验证long read(ByteBuffer[] dsts, int offset, int length）方法真苟同步特性-----------------------");
        //验证long read(ByteBuffer[] dsts, int offset, int length）方法真苟同步特性
        FileInputStream fileInputStream3 = new FileInputStream(new File("./ReadMethodTest2.txt"));
        FileChannel fileChannel3 = fileInputStream3.getChannel();

        for (int i = 0; i < 10; i++) {

            new Thread() {
                @Override
                public void run() {

                    try {
                        ByteBuffer byteBuffer4 = ByteBuffer.allocate(8);
                        ByteBuffer byteBuffer5 = ByteBuffer.allocate(8);
                        ByteBuffer[] byteBufferArray = {byteBuffer4, byteBuffer5};
                        long readLength = fileChannel3.read(byteBufferArray, 0, 2);
                        while (readLength != -1) {
                            synchronized (ReadMethodTest.class) {
                                for (int j = 0; j < byteBufferArray.length; j++) {
                                    byte[] bytes = byteBufferArray[j].array();
                                    for (int k = 0; k < bytes.length; k++) {
                                        System.out.print((char) bytes[k]);
                                    }
                                }
                                byteBuffer4.clear();
                                byteBuffer5.clear();
                                readLength = fileChannel3.read(byteBufferArray);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

         /*   new Thread() {
                @Override
                public void run() {

                    try {
                        ByteBuffer byteBuffer6 = ByteBuffer.allocate(8);
                        ByteBuffer byteBuffer7 = ByteBuffer.allocate(8);
                        ByteBuffer[] byteBufferArray2 = {byteBuffer6, byteBuffer7};
                        long readLength = fileChannel3.read(byteBufferArray2, 0, 2);
                        while (readLength != -1) {
                            synchronized (ReadMethodTest.class) {
                                for (int j = 0; j < byteBufferArray2.length; j++) {
                                    byte[] bytes = byteBufferArray2[j].array();
                                    for (int k = 0; k < bytes.length; k++) {
                                        System.out.print((char) bytes[k]);
                                    }
                                }
                                byteBuffer6.clear();
                                byteBuffer7.clear();
                                readLength = fileChannel3.read(byteBufferArray2);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();*/
        }
        Thread.sleep(3000);
        fileChannel3.close();
        fileInputStream3.close();
        //--------------------验证long read(ByteBuffer［］ dsts, int offset, int length ）方法从通道读取的数据大于缓冲区窑量-------------------

        System.out.println("--------------------验证long read(ByteBuffer［］ dsts, int offset, int length ）方法从通道读取的数据大于缓冲区窑量-------------------");

        FileInputStream fileInputStream4 = new FileInputStream(new File("./ReadMethodTest3.txt"));
        FileChannel fileChannel4 = fileInputStream4.getChannel();
        ByteBuffer byteBuffer4 = ByteBuffer.allocate(3);
        fileChannel4.read(byteBuffer4, 1);
        fileChannel4.close();
        fileInputStream4.close();
        byteBuffer4.rewind();
        for (int i = 0; i < byteBuffer4.limit(); i++) {
            System.out.print((char) byteBuffer4.get());
        }
        //---------------------------------验证read(ByteBuffer dst, long position ）方法从通道读取的字节放入缓冲区的 remaining 空间中----------------------------------
        System.out.println();
        System.out.println("---------------------------------验证read(ByteBuffer dst, long position ）方法从通道读取的字节放入缓冲区的 remaining 空间中----------------------------------");

        FileInputStream fileInputStream5 = new FileInputStream(new File("./ReadMethodTest4.txt"));
        FileChannel fileChannel5 = fileInputStream5.getChannel();
        ByteBuffer byteBuffer5 = ByteBuffer.allocate(100);
        byteBuffer5.position(1);
        byteBuffer5.limit(3);
        fileChannel5.read(byteBuffer5,2);
        fileChannel5.close();
        fileInputStream5.close();
        byteBuffer5.rewind();
        for (int i = 0; i < byteBuffer5.limit(); i++) {
            byte b = byteBuffer5.get();
            if (b==0){
                System.out.print("-");
            }else {
                System.out.print((char)b);
            }
        }

    }
}
