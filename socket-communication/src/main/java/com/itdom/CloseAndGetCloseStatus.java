package com.itdom;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *public void close （）方法的作用是关闭此套接字。在ac cept（） 中，所有当前阻塞的线程都
 * 将会抛出SocketException 。如果此套接字有一个与之关联的通道，则关闭该通道。
 * public boolean isClosed（）方法的作用是返回ServerSocket 的关闭状态。如果已经关闭了
 * 套接字， 则返回true 。
 */
public class CloseAndGetCloseStatus {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        System.out.println(serverSocket.isClosed());
        serverSocket.close();
        System.out.println(serverSocket.isClosed());
    }
}
