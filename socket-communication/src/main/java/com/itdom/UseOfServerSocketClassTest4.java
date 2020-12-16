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
 * 当前类是客户端，关联com.itdom.UseOfServerSocketClassTest3
 */
public class UseOfServerSocketClassTest4 {
    public static void main(String[] args) throws IOException, InterruptedException {
       //accept（）方法被调用了5 次，而构造方法的参数backlog 值却为3 ，实际也只能接受3
        //个连接的请求， 其他的连接请求被忽略。
        new Socket("localhost",8088);
        new Socket("localhost",8088);
        new Socket("localhost",8088);
        new Socket("localhost",8088);
        new Socket("localhost",8088);
        /**
         * 先运行程序UseOfServerSocketClassTest3然后马上运行当前程序
         *
         * 运行结果:
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
         * 	at com.itdom.UseOfServerSocketClassTest4.main(UseOfServerSocketClassTest4.java:27)
         */

    }
}
