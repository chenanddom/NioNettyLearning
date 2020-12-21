package com.itdom;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class EnableAndMaskNagleAlgorithmTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("192.168.10.54",8088));
        Socket socket = serverSocket.accept();
        System.out.println("A="+socket.getTcpNoDelay());
        socket.setTcpNoDelay(false);//立即发送，不会缓存数据，不启用Nagle算法
        System.out.println("B="+socket.getTcpNoDelay());
        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 50000; i++) {
            outputStream.write("1".getBytes());
        }
        socket.close();
        serverSocket.close();
    }
}
