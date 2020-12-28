package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 *SelectableChannel 类的public final SelectionKey register(Selector sel, int ops）方法的作用
 * 是向给定的选择器注册此通道，返回一个选择键（ Sel巳ctionKey ） 。
 * 参数sel 代表要向其注册此通道的选择器，参数ops 代表register（） 方法的返回值SelectionKey
 * 的可用操作集，操作集是在SelectionKey 类中以常量的形式进行提供的
 */
public class RegisterSelectorTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        /**此处必须设置成非阻塞的，不然会出现异常
         * Exception in thread "main" java.nio.channels.IllegalBlockingModeException
         * 	at java.nio.channels.spi.AbstractSelectableChannel.register(AbstractSelectableChannel.java:201)
         * 	at java.nio.channels.SelectableChannel.register(SelectableChannel.java:280)
         * 	at com.itdom.RegisterSelectorTest.main(RegisterSelectorTest.java:26)
         */
        serverSocketChannel.configureBlocking(true);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress("localhost",8088));

        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("selector="+selector);
        System.out.println("key="+selectionKey);
        serverSocket.close();
        serverSocketChannel.close();

    }
}
