package com.itdom;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * public void shutdownlnput（）方法的作用是将套接字的输入流置于“流的末尾EOF ”，也
 * 就是在套接字上调用shutdownlnput（）方法后从套接字输入流读取内容，流将返回EOF （文
 * 件结束符） 。发送到套接字的输入流端的任何数据都将在确认后被静默丢弃。调用此方法的
 * 一端进入半读状态（ read-half) ，也就是此端不能获得输入流，但对端却能获得输入流。一端
 * 能读，另一端不能读，称为半读。
 * public void shutdownOutput（）方法的作用是禁用此套接字的输出流。对于TCP 套接字，
 * 任何以前写人的数据都将被发送，并且后跟TCP 的正常连接终止序列。如果在套接字上调
 * 用shutdownOutput（）方法后写入套接字输出流，则该流将抛出IOException 。调用此方法的
 * 一端进入半写状态（ write -half）也就是此端不能获得输出流。但对端却能获得输出流。一
 * 端能写，另一端不能写，称为半写。
 */
public class EnableHalfReadHalfWriteTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        System.out.println("A="+inputStream.available());
        byte[] byteArray = new byte[2];
        int readLength = inputStream.read(byteArray);
        System.out.println("server get data:"+new String(byteArray,0,readLength));
//        socket.shutdownInput();//屏蔽inputStream，到达流的结尾
        System.out.println("B="+inputStream.available());
        readLength = inputStream.read(byteArray);
        System.out.println("readLength="+readLength);
        /**
         * 此处再次获取流就会出现错误
         * Exception in thread "main" java.net.SocketException: Socket input is shutdown
         * 	at java.net.Socket.getInputStream(Socket.java:907)
         * 	at com.itdom.EnableHalfReadHalfWriteTest.main(EnableHalfReadHalfWriteTest.java:33)
         */
        socket.getInputStream();
        socket.close();
        serverSocket.close();
    }
}
