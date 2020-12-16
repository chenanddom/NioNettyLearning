package com.itdom;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 本实验将实现Server 与Client 交换Userinfo 对象， 而不是前面章节S tring 类型的数据。
 * 正确的写法应该是：
 * 1 ）服务端先获得O bjectlnputStream 对象， 客户端就要先获得ObjectOutputStream 对象；
 * 2 ）服务端先获得Obj ectOutputS 仕earn 对象，客户端就要先获得ObjectinputStream 对象
 */
public class StreamSequenceForServerAndClientTest2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 8888);

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        for (int i = 0; i < 5; i++) {
            UserInfo newUserInfo = new UserInfo();
            newUserInfo.setId(i + 1);
            newUserInfo.setUsername("serverUsername" + (i + 1));
            newUserInfo.setPassword("serverPassword" + (i + 1));
            objectOutputStream.writeObject(newUserInfo);
            UserInfo userInfo = (UserInfo) objectInputStream.readObject();
            System.out.println("在客户端打印" + (i + 1) + ":" + userInfo.getId() + " " + userInfo.getUsername() + " " + userInfo.getPassword());
        }

        objectOutputStream.close();
        objectInputStream.close();
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
