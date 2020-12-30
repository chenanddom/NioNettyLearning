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
 * public final boolean isWritable（）方法的作用是测试此键的通道是否已准备好进行写入。
 * 调用此方法的形式为k.isWritable（），该调用与以下调用的作用完全相同： k.readyOps() &
 * OP WRITE!= 0 。如果此键的通道不支持写人操作，则此方法始终返回fal罚。返回值当且
 * 仅当readyOps () & OP_ WRITE 为非零值时才返回true 。
 */
public class JudgeReadyWriteTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel.configureBlocking(false);
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
    }
}
