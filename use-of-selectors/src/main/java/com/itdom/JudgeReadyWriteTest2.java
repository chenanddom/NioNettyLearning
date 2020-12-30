package com.itdom;

import sun.plugin2.ipc.windows.WindowsIPCFactory;

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
public class JudgeReadyWriteTest2 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost", 8888));
        while (true) {
            int keyCount = selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isConnectable()) {
                    if (socketChannel.isConnectionPending()) {
                        while (!socketChannel.finishConnect()) {
                            System.out.println("!socketChannel.finishConnect()......");
                        }
                        socketChannel.register(selector, SelectionKey.OP_WRITE);
                    }
                }
                if (key.isWritable()) {
                    System.out.println("client isWritable()");
                    SocketChannel channel = (SocketChannel) key.channel();
                    byte[] byteArray = "我是来自客户端，你好服务器".getBytes();
                    ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
                    channel.write(byteBuffer);
                    channel.close();
                }
            }
            System.out.println("client end !");
        }
    }
}
