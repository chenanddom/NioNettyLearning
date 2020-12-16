package com.itdom;

import java.net.InetSocketAddress;

/**
 * public static InetSocketAddress createUnresolved (String host, int port）方法的作用是根据
 * 主机名和端口号创建未解析的套接字地址，但不会尝试将主机名解析为InetAddress 。该方
 * 法将地址标记为未解析，有效端口值介于0 ～ 65535 之间。端口号0 代表允许系统在bind
 * 操作中随机挑选空闲的端口。
 * public final boolean isUnresolved（） 方法的作用：如果无法将主机名解析为InetAddress,
 * 则返回true 。
 */
public class UnresolvedSocketTest {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("www.baidu.com", 8080);
        System.out.println(inetSocketAddress.isUnresolved());
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress("www.baidu.com.adasfadsdasas", 8081);
        System.out.println(inetSocketAddress2.isUnresolved());
        InetSocketAddress inetSocketAddress3 = InetSocketAddress.createUnresolved("www.baidu.com",8082);
        System.out.println(inetSocketAddress3.isUnresolved());

        InetSocketAddress inetSocketAddress4 = InetSocketAddress.createUnresolved("www.baidu.com.12312321",8083);
        System.out.println(inetSocketAddress4.isUnresolved());
    }
}
