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
public class JudgeReadyReadTest2 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost",8888));
        int keyCount = selector.select();
        Set<SelectionKey> selectionKeySet = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeySet.iterator();
        while (iterator.hasNext()){
            SelectionKey key = iterator.next();
            if (key.isConnectable()){
                while (!socketChannel.finishConnect()){
                    System.out.println("!socketChannel.finishConnect()......");
                }
                System.out.println("client isConnectable()");
               SocketChannel channel = (SocketChannel) key.channel();
                byte[] byteArray = "我是来自客户端，你好服务器".getBytes();
                ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
                channel.write(byteBuffer);
                channel.close();
            }
        }
        System.out.println("client end!");
        /**
         * 先执行JudgeReadyReadTest再执行JudgeReadyReadTest2
         * 执行结果：
         * server isAcceptable()
         * server isReadable()
         * 我是来自客户端，你好服务器
         */
    }
}
