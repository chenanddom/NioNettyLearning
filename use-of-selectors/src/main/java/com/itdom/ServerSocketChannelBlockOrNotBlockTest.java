package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * public abstract SocketChannel accept （）方法的作用是接受此通道套接字的连接。如果此
 * 通道处于非阻塞模式，那么在不存在挂起的连接时，此方法将直接返回null 。否则，在新的
 * 连接可用或者发生1 /0 错误之前会无限期地阻塞它。无论此通道的阻塞模式如何，此方法返
 * 回的套接字通道（如果有）将处于阻塞模式。
 * 如何切换ServerSocketChannel 通道的阻塞与非阻塞的执行模式呢？调用ServerSocketChannel
 * 的public final SelectableChannel configureBlocking(boolean block）方法即可。public
 * final SelectableChannel configureBlocking(boolean block） 方法的作用是调整此通道的阻塞模
 * 式，传入true 是阻塞模式，传入false 是非阻塞模式。
 */
public class ServerSocketChannelBlockOrNotBlockTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        System.out.println(serverSocketChannel.isBlocking());
        //阻塞模式
        /**
         * 执行结果：
         * true
         * begin1608692410764
         */
//        serverSocketChannel.configureBlocking(true);
        /**
         * 非阻塞模式
         * 执行结果:
         *true
         * begin1608692446776
         *  end 1608692446776 socketChannel=null
         * Exception in thread "main" java.lang.NullPointerException
         * 	at com.itdom.ServerSocketChannelBlockOrNotBlockTest.main(ServerSocketChannelBlockOrNotBlockTest.java:40)
         */
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost",8088),-1);
        System.out.println("begin"+System.currentTimeMillis());
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println(" end "+System.currentTimeMillis()+" socketChannel="+socketChannel);
        socketChannel.close();
        serverSocketChannel.close();
    }
}
