package com.itdom;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

/**
 * 获得IP 地址信息
 * getlnetAddress()方法用来获取S ocket 绑定的本地IP 地址信息。如果Socket 是未绑定
 * 的，则该方法返回null 。
 */
public class GetIpInfo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("192.168.10.54",8088));
        System.out.println(serverSocket.getInetAddress().getHostAddress());
        /**这是一个静态方法
         *   public static InetAddress getLoopbackAddress() {
         *         return impl.loopbackAddress();
         *     }
         */
        System.out.println(InetAddress.getLoopbackAddress());
        /**
         * 执行结果:
         * 192.168.10.54
         * localhost/127.0.0.1
         */

    }
}