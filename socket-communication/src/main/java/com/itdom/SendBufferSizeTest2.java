package com.itdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket 中的SO_RCVB 旧选项是设置接收缓冲区的大小的，而SO_SNDBUF 选项是设
 * 置发送缓冲区的大小的。
 * public synchronized void setSendBufferSize(int size）方法的作用是将此Socket 的SO_SND B
 * 旧选项设置为指定的值。平台的网络连接代码将SO SNDB四选项用作设置底层网络νo
 * 缓存的大小的提示。由于SO SNDB旧是一种提示，因此想要验证缓冲区设置大小的应用程
 * 序应该调用getSendBufferSize（）方法。参数size 用来设置发送缓冲区的大小， 此值必须大于0。
 * public int getSendBufferSizeO 方法的作用是获取此Socket 的SO_SNDBUF 选项的值，该值
 * 是平台在Socket 上输出时使用的缓冲区大小。返回值是此Socket 的SO_SNDB旧选项的值。
 */
public class SendBufferSizeTest2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        System.out.println("A client socket.getSendBufferSize()="+socket.getSendBufferSize());
        socket.setSendBufferSize(1024*1024);
        System.out.println("B client socket.getSendBufferSize()="+socket.getSendBufferSize());
        socket.connect(new InetSocketAddress("localhost",8088));
        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 500000; i++) {
            outputStream.write("12738912738912839012748365782374928489238439247289347323242342".getBytes());
            System.out.println(i+1);
        }
        outputStream.write("end!".getBytes());
        outputStream.close();
        socket.close();
    }
}
