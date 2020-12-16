package com.itdom;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *ServerSocket 类中有很多方法，熟悉这些方法的功能与使用是掌握Socket 的基础，下
 * 面就开始介绍其常用的API 方法
 */
public class UseOfServerSocketClassTest {
    public static void main(String[] args)  {
        /**
         * 4.2.1 接受accept 与超时Timeout
         * public Socket accept （）方法的作用就是侦昕并接受此套接字的连接。此方法在连接传人
         * 之前一直阻塞。
         * setSoTimeout (timeout） 方法的作用是设置超时时间，通过指定超时timeout 值启用／禁
         * 用SO TIMEOUT ，以ms 为单位。在将此选项设为非零的超时tim e ou t 值时，对此S erv erSocket
         * 调用accept （） 方法将只阻塞timeout 的时间长度。如果超过超时值，将引发j ava . net.
         * SocketTimeoutException ， 但Se凹erSocket 仍旧有效，在结合try-catch 结构后， 还可以继续进
         * 行accept（）方法的操作。SO_TIMEOU T 选项必须在进入阻塞操作前被启用才能生效。注意，
         * 超时值必须是大于0 的数。超时值为0 被解释为无穷大超时值。参数int timeout 的作用是在指
         * 定的时间内必须有客户端的连接请求，超过这个时间即出现异常，默认值是0 ， 即永远等待。
         * int getSoTimeout （） 方法的作用是获取SO TIM E OUT 的设置。返回0 意味着禁用了选项
         * （ 即无穷大的超时值） 。
         */
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println(serverSocket.getSoTimeout());
            serverSocket.setSoTimeout(4000);
            System.out.println(serverSocket.getSoTimeout());
            System.out.println();
            System.out.println("begin " + System.currentTimeMillis());
            serverSocket.accept();
            System.out.println(" end " + System.currentTimeMillis());

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("catch "+System.currentTimeMillis());
        }
        /**
         * 执行结果:
         * 0
         * 4000
         *
         * begin 1607389975096
         * java.net.SocketTimeoutException: Accept timed out
         * 	at java.net.DualStackPlainSocketImpl.waitForNewConnection(Native Method)
         * 	at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:135)
         * 	at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
         * 	at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
         * 	at java.net.ServerSocket.implAccept(ServerSocket.java:545)
         * 	at java.net.ServerSocket.accept(ServerSocket.java:513)
         * 	at com.itdom.UseOfServerSocketClassTest.main(UseOfServerSocketClassTest.java:33)
         * catch 1607389979097
         */




    }
}
