package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

/**
 * public abstract ServerSocketChannel bind(SocketAddress local, int backlog）方法的作用
 * 是将通道的套接字绑定到本地地址并侦昕连接，通过使用参数backlog 来限制客户端连接的
 * 数量。
 */
public class ServerSocketChannelBacklogTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8088),60);
        ServerSocket serverSocket = serverSocketChannel.socket();
        Thread.sleep(10000);
        System.out.println("begin.......");
        boolean isRun=true;
        while (isRun){
            Socket socket = serverSocket.accept();
            socket.close();
        }
        Thread.sleep(8000);
        serverSocket.close();
        serverSocketChannel.close();

    }
}
