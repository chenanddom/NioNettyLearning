package com.itdom;


import java.io.IOException;
import java.net.ServerSocket;

/**
 * Socket 的客户端。在代码层面使用的方式就是使用Socket 类去连接ServerSocket 类，也就
 * 是客户端要主动连接服务端。
 * ServerSocket 类中的public Sock 巳t accept （）方法的作用是侦昕并接受此套接字的连接。
 * 此方法在连接传人之前一直阻塞。public Socket accept（）方法的返回值是Socket 类型。
 * 在本实验中，将验证ServerSocket 类中的accept （）方法具有阻塞特性，也就是当没有客
 * 户端连接服务端时，呈阻塞状态。
 */
public class AcceptMethodOfServerSocketBlockTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        System.out.println("server阻塞开始="+System.currentTimeMillis());
        serverSocket.accept();
        System.out.println("server阻塞结束="+System.currentTimeMillis());
        /**
         * 运行结果：
         *程序阻塞在了accept方法这个地方，无法正常的往下走。
         *
         */

    }


}
