package com.itdom;

import java.io.*;
import java.net.Socket;

/**
 * 本实验要实现的是客户端向服务器端传递PNG 图片文件， 练习一下使用Socket 传递字
 * 节数据。
 */
public class UsingSocketTransferPngTest2 {
    public static void main(String[] args) throws IOException {
        String filePath = "F:\\home_page\\陈东.png";
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        byte[] byteArray = new byte[2048];
        System.out.println("socket begin "+System.currentTimeMillis());
        Socket socket = new Socket("localhost", 8088);
        OutputStream outputStream = socket.getOutputStream();
        int readLength = fileInputStream.read(byteArray);
        while (readLength!=-1){
            outputStream.write(byteArray,0,readLength);
            readLength = fileInputStream.read(byteArray);
        }
        outputStream.close();
        fileInputStream.close();
        socket.close();
    }
}
