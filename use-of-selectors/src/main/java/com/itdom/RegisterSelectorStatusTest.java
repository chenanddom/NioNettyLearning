package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 判断注册的状态
 * SelectableChannel 类的public final boolean isRegistered（）方法的作用是判断此通道当前是否
 * 已向任何选择器进行了注册。新创建的通道总是未注册的。由于对SelectionKey 执行取消操作
 * 和通道进行注销之间有延迟，因此在已取消某个通道的所有SelectionKey 后，该通道可能在一
 * 定时间内还会保持已注册状态。关闭通道后，该通道可能在一定时间内还会保持已注册状态。
 */
public class RegisterSelectorStatusTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost",8088));
        System.out.println("A isRegistered:"+serverSocketChannel.isRegistered());
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("A isRegistered:"+serverSocketChannel.isRegistered());

        serverSocket.close();
        serverSocketChannel.close();
        /**
         * 执行结果：
         * A isRegistered:false
         * A isRegistered:true
         */
    }
}
