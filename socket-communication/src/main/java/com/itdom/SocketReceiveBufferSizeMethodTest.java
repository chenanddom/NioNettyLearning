package com.itdom;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * public void setReceiveBufferSize (int size）方法的作用是为从此ServerSocket 接受的套
 * 接字的SO RCVBUF 选项设置新的建议值。在接受的套接字中，实际被采纳的值必须在
 * accept（）方法返回套接字后通过调用Socket.getReceiveBufferSize（）方法进行获取。
 * SO RCVB町的值用于设置内部套接字接收缓冲区的大小和设置公布到远程同位体的TCP
 * 接收窗口的大小。随后可以通过调用Socket.setReceiveBufferSize (int）方法更改该值。但是，如
 * 果应用程序希望允许大于盯c 1323 中定义的64阻的接收窗口， 则在将ServerSocket 绑定到
 * 本地地址之前必须在其中设置建议值。这意味着，必须用无参数构造方法创建ServerSocket ,
 * 然后必须调用setReceiveBufferSizeO 方法，最后通过调用bind（） 将ServerSocket 绑定到地址。
 * 如果不是按照前面的顺序设置接收缓冲区的大小，也不会导致错误，缓冲区大小可能被设置为
 * 所请求的值，但是此ServerSocket 中接受的套接字中的TCP 接收窗口将不再大于64阻。
 * public int getReceiveBufferSize （）方法的作用是获取此ServerSocket 的SO_RCVBUF 选
 * 项的值，该值是将用于从此ServerSocket 接受的套接字的建议缓冲区大小。
 * 在接受的套接字中，实际设置的值通过调用Socket. getRec巳iveBufferSize（）方法来确定。
 */
public class SocketReceiveBufferSizeMethodTest {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReceiveBufferSize(66);
            System.out.println("A server serverSocket.getReceiveBufferSize()="+serverSocket.getReceiveBufferSize());
            serverSocket.bind(new InetSocketAddress("localhost",8088));
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            char[] charArray = new char[1024];
            int readLength = inputStreamReader.read(charArray);
            while (readLength!=-1){
                String newString = new String(charArray, 0, readLength);
                System.out.println(newString);
                readLength = inputStreamReader.read(charArray);
            }
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
