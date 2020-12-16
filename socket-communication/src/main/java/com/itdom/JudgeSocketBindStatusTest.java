package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * 判断Socket 绑定状态
 * public boolean isBound（）方法的作用是返回ServerSocket 的绑定状态。如果将ServerSocket
 * 成功地绑定到一个地址，则返回true 。
 */
public class JudgeSocketBindStatusTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        System.out.println("bind begin "+serverSocket.isBound());
//        serverSocket.bind(new InetSocketAddress("localhost",8088));
        //如果绑定了不存在的地址就会出错
        /*
        Exception in thread "main" java.net.SocketException: Unresolved address
	    at java.net.ServerSocket.bind(ServerSocket.java:368)
	    at java.net.ServerSocket.bind(ServerSocket.java:329)
	    at com.itdom.JudgeSocketBindStatusTest.main(JudgeSocketBindStatusTest.java:18)
         */
        serverSocket.bind(new InetSocketAddress("www.baidu.com.xxxxx",8088));
        System.out.println("bind end "+serverSocket.isBound());
    }
}
