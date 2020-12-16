package com.itdom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 测试客户端合服务端多次交互
 */
public class MultipleInteractionsBetweenClientAndServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        int byteLength = objectInputStream.readInt();
        byte[] bytesArray = new byte[byteLength];
        objectInputStream.readFully(bytesArray);
        String newString = new String(bytesArray);
        System.out.println(newString);
        //输入结束

        //输出开始
        OutputStream outputStream = socket.getOutputStream();
        String strA = "客户端你好A\n";
        String strB = "客户端你好B\n";
        String strC = "客户端你好C\n";
        int allStrByteLength = (strA + strB + strC).getBytes().length;
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeInt(allStrByteLength);
        objectOutputStream.flush();
        objectOutputStream.write(strA.getBytes());
        objectOutputStream.write(strB.getBytes());
        objectOutputStream.write(strC.getBytes());
        objectOutputStream.flush();
        //输出结束

        //输入开始
        byteLength = objectInputStream.readInt();
        bytesArray = new byte[byteLength];
        objectInputStream.readFully(bytesArray);
        newString = new String(bytesArray);
        System.out.println(newString);
        //输入结束
        //输出开始
        strA = "客户端你好D\n";
        strB = "客户端你好E\n";
        strC = "客户端你好F\n";

        allStrByteLength = (strA + strB + strC).getBytes().length;
        objectOutputStream.writeInt(allStrByteLength);
        objectOutputStream.flush();
        objectOutputStream.write(strA.getBytes());
        objectOutputStream.write(strB.getBytes());
        objectOutputStream.write(strC.getBytes());
        objectOutputStream.flush();
        //输出结束
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
