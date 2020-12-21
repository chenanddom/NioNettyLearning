package com.itdom;

import java.io.IOException;
import java.net.*;

/**
 * public InetAddress getLocalAddress（）方法的
 * 作用是获取套接字绑定的本地InetAddress 地址
 * 信息。
 * public SocketAddr巳ss getLocalSocketAddress()
 * 方法的作用是返回此套接字绑定的端点的Socket-Address 地址信息。如果尚未绑定， 则返回
 * null 。
 */
public class GetInetAddressAndSocketAddressTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("192.168.10.54",8088));
        Socket socket = serverSocket.accept();
        InetAddress inetAddress = socket.getLocalAddress();
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getLocalSocketAddress();
        //getHostName()回去进行DNS的域名解析
        System.out.println(inetSocketAddress.getHostName());
        byte[] address = inetAddress.getAddress();
        System.out.print("server Ip address is:");
        for (int i = 0; i < address.length; i++) {
            System.out.println(address[i]+" ");
        }
        System.out.println();
        System.out.println("server port is:"+inetSocketAddress.getPort());
        System.out.println(inetSocketAddress.getHostString());
        socket.close();
        serverSocket.close();
    }
}
