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
public class ServerSocketChannelBacklogTest2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            Socket socket = new Socket("localhost", 8088);
            socket.close();
            System.out.println("客户端连接个数为:"+(i+1));
        }
        /**
         * 先执行程序ServerSocketChannelBacklogTest再执行ServerSocketChannelBacklogTest2
         * 执行结果：
         * 客户端连接个数为:1
         * ...
         * 客户端连接个数为:40
         * 客户端连接个数为:41
         * 客户端连接个数为:42
         * 客户端连接个数为:43
         * 客户端连接个数为:44
         * 客户端连接个数为:45
         * 客户端连接个数为:46
         * 客户端连接个数为:47
         * 客户端连接个数为:48
         * 客户端连接个数为:49
         * 客户端连接个数为:50
         * 客户端连接个数为:51
         * 客户端连接个数为:52
         * 客户端连接个数为:53
         * 客户端连接个数为:54
         * 客户端连接个数为:55
         * 客户端连接个数为:56
         * 客户端连接个数为:57
         * 客户端连接个数为:58
         * 客户端连接个数为:59
         * 客户端连接个数为:60
         * Exception in thread "main" java.net.ConnectException: Connection refused: connect
         * 	at java.net.DualStackPlainSocketImpl.connect0(Native Method)
         * 	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:79)
         * 	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350)
         * 	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206)
         * 	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188)
         * 	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:172)
         * 	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
         * 	at java.net.Socket.connect(Socket.java:589)
         * 	at java.net.Socket.connect(Socket.java:538)
         * 	at java.net.Socket.<init>(Socket.java:434)
         * 	at java.net.Socket.<init>(Socket.java:211)
         * 	at com.itdom.ServerSocketChannelBacklogTest2.main(ServerSocketChannelBacklogTest2.java:17)
         */
    }
}
