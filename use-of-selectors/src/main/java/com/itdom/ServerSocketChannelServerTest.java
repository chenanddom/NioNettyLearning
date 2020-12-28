package com.itdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

/**
 *ServerSocketChannel 类并不能直接new I 73
 * 实例化，但API 中提供了public static ServerSocketChannel open()（）方法来创建Serve rSocketChannel 类的
 * 实例。open()方法是静态的，作用是打开服务器套接字通道。新通道的套接字最初是未绑定的；可以接受连接之前，必须通过它的某个套接字的bind（）方法将其绑定到具体的地址。
 *通过调用open()方法创建ServerSocketChannel 类的实例后，可以调用它的public
 * abstract ServerSock巳t socket()方法来返回ServerSocket 类的对象， 然后与客户端套接字
 * 进行通信。socket()方法的作用是获取与此通道关联的服务器套接字ServerSocket 类的
 * 对象。
 * public final void close()方法的作用是关闭此通道。如果己关闭该通道，则此方法立即
 * 返回。否则，它会将该通道标记为己关闭，然后调用imp!CloseChannel （）方法以完成关闭
 * 操作。
 */
public class ServerSocketChannelServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost",8088));
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        char[] charArray = new char[1024];
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        int readLength = inputStreamReader.read(charArray);
        while (readLength!=-1){
            String newString = new String(charArray,0,readLength);
            System.out.println(newString);
            readLength = inputStreamReader.read(charArray);
        }
        inputStreamReader.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        serverSocketChannel.close();
    }
}
