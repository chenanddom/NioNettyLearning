package com.itdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *Socket 中的SO_RCVB 旧选项是设置接收缓冲区的大小的，而SO_SNDBUF 选项是设置发送缓冲区的大小的。
 * public synchronized void setSendBufferSize(int size）方法的作用是将此Socket 的SO_SND B
 * 旧选项设置为指定的值。平台的网络连接代码将SO SNDB四选项用作设置底层网络νo
 * 缓存的大小的提示。由于SO SNDB旧是一种提示，因此想要验证缓冲区设置大小的应用程
 * 序应该调用getSendBufferSize（）方法。参数size 用来设置发送缓冲区的大小， 此值必须大于0。
 * public int getSendBufferSizeO 方法的作用是获取此Socket 的SO_SNDBUF 选项的值，该值
 * 是平台在Socket 上输出时使用的缓冲区大小。返回值是此Socket 的SO_SNDB旧选项的值。
 */
public class SocketOptionSendBuffersizeTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        char[] charArray = new char[1024*1024];
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        int readLength = inputStreamReader.read(charArray);
        long beginTime = System.currentTimeMillis();
        while (readLength!=-1){
            String newString = new String(charArray, 0, readLength);
            System.out.println(newString);
            readLength = inputStreamReader.read(charArray);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
        socket.close();
        serverSocket.close();
    }
}
