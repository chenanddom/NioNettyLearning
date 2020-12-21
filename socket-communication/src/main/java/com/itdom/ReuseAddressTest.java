package com.itdom;

import com.sun.org.apache.bcel.internal.generic.FADD;

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
public class ReuseAddressTest {
    public static void main(String[] args) throws InterruptedException {
        Thread serverThread = new Thread() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket();
                    //不允许端口复用
                    serverSocket.setReuseAddress(false);
                    serverSocket.bind(new InetSocketAddress("localhost", 8088));
                    Socket socket = serverSocket.accept();
                    Thread.sleep(1000);
                    socket.close();//服务端首先主动关闭连接
                    serverSocket.close();//服务端首先主动关闭连接
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        serverThread.start();

        Thread.sleep(5000);
        Thread client = new Thread() {
            @Override
            public void run() {
                try {
//                    Socket socket = new Socket("localhost", 8088);
                    Socket socket = new Socket();
                    /**
                     * 因为Windows 操作系统并没有完全实现BSD Socket 的标准，所以意味着在
                     * Windows 操作系统中不能使用setReuseAddress (boolean）方法来实现端口复用。
                     */
                    socket.setReuseAddress(true);
                    socket.bind(new InetSocketAddress(7777));
                    socket.connect(new InetSocketAddress("localhost",8088));
                    System.out.println("socket.getLocalPort()="+socket.getLocalPort());

//                    Thread.sleep(3000);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } /*catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        };
        client.start();
        /**
         * 执行的结果：
         * java.net.BindException: Address already in use: connect
         * 	at java.net.DualStackPlainSocketImpl.connect0(Native Method)
         * 	at java.net.DualStackPlainSocketImpl.socketConnect(DualStackPlainSocketImpl.java:79)
         * 	at java.net.AbstractPlainSocketImpl.doConnect(AbstractPlainSocketImpl.java:350)
         * 	at java.net.AbstractPlainSocketImpl.connectToAddress(AbstractPlainSocketImpl.java:206)
         * 	at java.net.AbstractPlainSocketImpl.connect(AbstractPlainSocketImpl.java:188)
         * 	at java.net.PlainSocketImpl.connect(PlainSocketImpl.java:172)
         * 	at java.net.SocksSocketImpl.connect(SocksSocketImpl.java:392)
         * 	at java.net.Socket.connect(Socket.java:589)
         * 	at java.net.Socket.connect(Socket.java:538)
         * 	at com.itdom.ReuseAddressTest$2.run(ReuseAddressTest.java:69)
         */
    }
}
