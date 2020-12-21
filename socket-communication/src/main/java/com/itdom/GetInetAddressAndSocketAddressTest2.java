package com.itdom;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * public InetAddress getLocalAddress（）方法的
 * 作用是获取套接字绑定的本地InetAddress 地址
 * 信息。
 * public SocketAddr巳ss getLocalSocketAddress()
 * 方法的作用是返回此套接字绑定的端点的Socket-Address 地址信息。如果尚未绑定， 则返回
 * null 。
 */
public class GetInetAddressAndSocketAddressTest2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.10.54", 8088);
        socket.close();
    }
}
