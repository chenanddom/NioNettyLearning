package com.itdom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在不更改参数backlo g 设置的情况下，其默认值是50 。
 * 需要注意的是， backlog 限制的连接数量是由操作系统进行
 * 处理的，因为backlog 最终会传递给用native 声明的方法
 */
public class DefaultBacklogTest2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 100; i++) {
            Socket socket = new Socket("localhost", 8088);
            System. out. println( "client 发起连接"+(i+1));
        }
        /**
         * 执行结果:
         * client 发起连接1
         * client 发起连接2
         * client 发起连接3
         * client 发起连接4
         * client 发起连接5
         * client 发起连接6
         * client 发起连接7
         * client 发起连接8
         * client 发起连接9
         * client 发起连接10
         * client 发起连接11
         * client 发起连接12
         * client 发起连接13
         * client 发起连接14
         * client 发起连接15
         * client 发起连接16
         * client 发起连接17
         * client 发起连接18
         * client 发起连接19
         * client 发起连接20
         * client 发起连接21
         * client 发起连接22
         * client 发起连接23
         * client 发起连接24
         * client 发起连接25
         * client 发起连接26
         * client 发起连接27
         * client 发起连接28
         * client 发起连接29
         * client 发起连接30
         * client 发起连接31
         * client 发起连接32
         * client 发起连接33
         * client 发起连接34
         * client 发起连接35
         * client 发起连接36
         * client 发起连接37
         * client 发起连接38
         * client 发起连接39
         * client 发起连接40
         * client 发起连接41
         * client 发起连接42
         * client 发起连接43
         * client 发起连接44
         * client 发起连接45
         * client 发起连接46
         * client 发起连接47
         * client 发起连接48
         * client 发起连接49
         * client 发起连接50
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
         * 	at com.itdom.DefaultBacklogTest2.main(DefaultBacklogTest2.java:15)
         */
    }
}
