package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * public final boolean isReadable()方法的作用是测试此键的通道是否已准备好进行读取。
 * 调用此方法的形式为k.isReadable() ，该调用与以下调用的作用完全相同： k.readyOps() &
 * OP READ!= 0 。如果此键的通道不支持读取操作，则此方法始终返回false 。返回值当且仅
 * 当readyOps() & OP _READ 为非零值时才返回true
 */
public class JudgeReadyReadTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        SocketChannel socketChannel = null;
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    System.out.println("server isAcceptable()");
                    socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    System.out.println("server isReadable()");
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int readLength = socketChannel.read(byteBuffer);
                    while (readLength != -1) {
                        String newString = new String(byteBuffer.array(), 0, readLength);
                        System.out.println(newString);
                        readLength = socketChannel.read(byteBuffer);
                    }
                    socketChannel.close();
                }
                iterator.remove();
            }
        }
        /**
         * 先执行JudgeReadyReadTest再执行JudgeReadyReadTest2
         * 执行结果：
         * server isAcceptable()
         * server isReadable()
         * 我是来自客户端，你好服务器
         */
    }
}
