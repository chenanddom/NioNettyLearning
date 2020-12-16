package com.itdom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *构造方法的backl og 参数含义
 * public ServerSocket( i nt port, int backlog)
 * 中的参数backlog 的主要作用就是允许接受客户端连
 * 接请求的个数。客户端有很多连接进入到操作系统
 * 中，将这些连接放人操作系统的队列中，当执行accept（）方法时，允许客户端连接的个数要
 * 取决于backlog 参数。
 * 利用指定的backlog 创建服务器套接字并将其绑定到指定的本地端口号port 。对port 端
 * 口参数传递值为0 ，意味着将自动分配空闲的端口号。
 * 传入backlog 参数的作用是设置最大等待队列长度，如果队列己满，则拒绝该连接。
 * backlog 参数必须是大于0 的正值，如果传递的值等于或小于0 ，则使用默认值50 。
 * 当前类主要是服务端主要关联com.itdom.UseOfServerSocketClassTest4，
 */
public class UseOfServerSocketClassTest3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8088, 3);
        /**
         * sleep的作用是不让ServerSocket调用accept()方法，而是由客户端先发起10个连接请求
         * 然后执行accept()操作（只能接受3个连接）
         */
        Thread.sleep(10000);

        System.out.println(" accept1 begin");
        Socket socket = serverSocket.accept();
        System.out.println(" accept1 end");

        System.out.println(" accept2 begin");
        Socket socket2 = serverSocket.accept();
        System.out.println(" accept2 end");

        System.out.println(" accept3 begin");
        Socket socket3 = serverSocket.accept();
        System.out.println(" accept3 end");


        System.out.println(" accept4 begin");
        Socket socket4 = serverSocket.accept();
        System.out.println(" accept4 end");

        System.out.println(" accept5 begin");
        Socket socket5 = serverSocket.accept();
        System.out.println(" accept5 end");

        socket.close();
        socket2.close();
        socket3.close();
        socket4.close();
        socket5.close();
        serverSocket.close();
    }
}
