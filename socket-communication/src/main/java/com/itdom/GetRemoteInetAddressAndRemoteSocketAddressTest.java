package com.itdom;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * public InetAddress getlnetAddress （）方法的作用是返回此套接字连接到的远程的In etAddress
 * 地址。如果套接字是未连接的，则返回null 。
 * public SocketAddress getRemoteSocketAddress （）方法的作用是返回此套接字远程端点的
 * SocketAddress 地址，如果未连接， 则返回null 。
 */
public class GetRemoteInetAddressAndRemoteSocketAddressTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        InetAddress inetAddress = socket.getInetAddress();
        InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
        byte[] address = inetAddress.getAddress();
        for (int i = 0; i < address.length; i++) {
            System.out.print(address[i]+" ");
        }
        System.out.println();
        System.out.println("client's port is:"+inetSocketAddress.getPort());
        socket.close();
        serverSocket.close();
    }
}
