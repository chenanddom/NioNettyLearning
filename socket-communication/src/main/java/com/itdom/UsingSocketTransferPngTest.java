package com.itdom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 本实验要实现的是客户端向服务器端传递PNG 图片文件， 练习一下使用Socket 传递字
 * 节数据。
 */
public class UsingSocketTransferPngTest {
    public static void main(String[] args) throws IOException {
        byte[] byteArray = new byte[2048];
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        int readLength = inputStream.read(byteArray);
        FileOutputStream fileOutputStream = new FileOutputStream(new File("F:\\test\\test.png"));
        while (readLength!=-1){
            fileOutputStream.write(byteArray,0,readLength);
            readLength = inputStream.read(byteArray);
        }
        fileOutputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
