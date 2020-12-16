package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * public void setReuseAddress (boolean on）方法的作用是启用／禁用SO_REUSEADDR 套
 * 接字选项。关闭TC P 连接时，该连接可能在关闭后的一段时间内保持超时状态（通常称为
 * TIME_ WAIT 状态或2MSL 等待状态） 。对于使用已知套接字地址或端口的应用程序而言，
 * 如果存在处于超时状态的连接（包括地址和端口），则应用程序可能不能将套接字绑定到所
 * 需的SocketAddres s 上。
 * 如果在使用bind (SocketAddress）方法‘绑定套接字之前’ 启用SO_REUSEADDR 选项，
 * 就可以允许绑定到处于超时状态的套接字。
 * 当创建ServerSocket 时， SO REUSEADDR 的初始设置是不确定的，要依赖于操作系
 * 统的实现。在使用bind（）方法绑定套接字后，启用或禁用SO_REUSEADDR 时的行为是不
 * 确定的，也要依赖于操作系统的实现。
 * 应用程序可以使用getReuseAddress（）来判断SO_REUSEADDR 的初始设置。
 * public boolean getReuseAddress（）方法的作用是测试是否启用s。一阻USEADDR 。
 * 在调用Socket 类的c lose （）方法时·，会关闭当前连接，释放使用的端口，但在操作系统
 * 层面，并不会马上释放当前使用的端口。如果端口呈TIME WAIT 状态，则在Linux 操作系
 * 统中可以重用此状态的端口。setReuseAddress (boolean）方法就是用来实现这样的功能的，
 * 也就是端口复用。端口复用的优点是可以大幅提升端口的使用率，用较少的端口数完成更多
 * 的任务。
 * 什么是TIME WAIT 状态？服务端（ Server）与客户端（ Client ）建立TCP 连接之后，主
 * 动关闭连接的一方就会进入TIME WAIT 状态。例如，客户端主动关闭连接时，会发送最后
 * 一个ACK ，然后客户端就会进入TIME WAIT 状态，再“停留若干时间’，然后进入CLOSED
 * 状态。在Linux 操作系统中，当在“停留若干时间”段时，应用程序是可以复用呈TIME
 * WAIT 状态的端口的，这样可提升端口利用率。
 */
public class ReuseAddressTest2 {
    public static void main(String[] args) throws InterruptedException, IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        System.out.println("accept begin");
        Socket socket = serverSocket.accept();
        System.out.println("accept end");
        socket.close();
        serverSocket.close();
    }
}
