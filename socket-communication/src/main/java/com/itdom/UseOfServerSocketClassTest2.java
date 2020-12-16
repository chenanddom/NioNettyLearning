package com.itdom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *ServerSocket 类中有很多方法，熟悉这些方法的功能与使用是掌握Socket 的基础，下
 * 面就开始介绍其常用的API 方法
 */
public class UseOfServerSocketClassTest2 {
    public static void main(String[] args)  {
       try {
           System.out.println("client begin "+System.currentTimeMillis());
           Socket socket = new Socket("localhost", 8000);
           System.out.println("client end "+System.currentTimeMillis());
       }catch (IOException e){
           e.printStackTrace();
           System.out.println("catch "+System.currentTimeMillis());
       }
        /**
         * 执行结果：
         * 首先运行Server 类的实现代码，等待4s 后出现异常，结果如图4- 31 所示。
         * 出现“ java . net.SocketTimeoutException: Accept timed out ”异常的原因是在4s 之内并
         * 没有客户端连接服务端。
         * 再次运行Server 类的实现代码，然后以最快的速度运行Client
         * 类的实现代码：
         * client begin 1607390263358
         * client end 1607390263372
         */

        
    }
}
