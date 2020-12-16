package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

/**
 *public void bind (SocketAddress endpoint）方法的主要作用是将ServerSocket 绑定到特定
 * 的Socket 地址(Ip 地址和端口号），使用这个地址与客户端进行通信。如果地址为null ，则
 * 系统将挑选一个临时端口和一个有效本地地址来绑定套接字。
 *该方法的使用场景就是在使用ServerS ocket 类的无参构造方法后想指定本地端口。
 * 因为SocketAddress 类表示不带任何协议附件的Socket Address ，所以SocketAddress 类
 * 的源代码非常简单，如下：
 * public abstract class SocketAddress implements java.io.Serializable {
 *
 *     static final long serialVersionUID = 5215720748342549866L;
 *
 * }
 *
 * 作为一个抽象（ abstract ）类，应通过特定的、协议相
 * 关的实现为其创建子类。它提供不可变对象，供套接字用
 * 于绑定、连接或用作返回值。
 * SocketAddress 类是抽象类，其相关信息如图4-37 所示。
 * SocketAddress 类有1 个子类InetSocketAddress
 *
 */
public class BindToSpecifiedSocketAddressTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        System.out.println("new ServerSocket()无参构造的端口是："+serverSocket.getLocalPort());
        serverSocket.bind(new InetSocketAddress("192.168.10.54",8888));
        System.out.println("调用bind方法绑定的端口是："+serverSocket.getLocalPort());
        InetSocketAddress inetSocketAddress = (InetSocketAddress)serverSocket.getLocalSocketAddress();
        System.out.println("inetSockerAddress.getHostName="+inetSocketAddress.getHostName());
        System.out.println("inetSockerAddress.getHostString="+inetSocketAddress.getHostString());
        System.out.println("inetSocketAddress.getPort="+inetSocketAddress.getPort());
        serverSocket.close();
        /**
         * 执行结果:
         * new ServerSocket()无参构造的端口是：-1
         * 调用bind方法绑定的端口是：8888
         * inetSockerAddress.getHostName=CHEND-PC.sen5.sz
         * inetSockerAddress.getHostString=CHEND-PC.sen5.sz
         * inetSocketAddress.getPort=8888
         */
    }
}
