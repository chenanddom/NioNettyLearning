package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 如果两个不同的通道注册到相同的选择器，那么极易出现重复消费的情况。
 */
public class RepeatedConsumptionTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel1.configureBlocking(false);
        serverSocketChannel1.bind(new InetSocketAddress("localhost", 7077));

        ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.configureBlocking(false);
        serverSocketChannel2.bind(new InetSocketAddress("localhost", 8088));


        Selector selector = Selector.open();

        serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel2.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int keyCount = selector.select();
            /**
             * 进入了循环体之后就是获取键值得集合一直都是指向得同一个对象。
             */
            Set<SelectionKey> keys = selector.keys();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("keyCount=" + keyCount);
            System.out.println("set1.size()=" + keys.size());
            System.out.println("set2.size()=" + selectionKeys.size());
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel channel = serverSocketChannel.accept();
                if (channel == null) {
                    System.out.println("打印这条信息证明是连接8088服务器时，重复消费的情况发生，");
                    System.out.println("将7077关联的selectionKey对应的SocketChannel通道取出来");
                    System.out.println("但是值为null, socketChannel == null.");
                } else {
                    InetSocketAddress socketAddress = (InetSocketAddress) serverSocketChannel.getLocalAddress();
                    System.out.println(socketAddress.getPort() + " 被客户端连接了!");
                    System.out.println();
                }
                //将已经处理的键从集合删除就可以避免出现重复消费的现象
                iterator.remove();
            }
        }
        /**
         * 首先运行本程序，然后运行RepeatedConsumptionTest2，然后在运行RepeatedConsumptionTest3
         * 运行结果：
         * keyCount=1
         * set1.size()=2
         * set2.size()=1
         * 7077 被客户端连接了!
         *
         * keyCount=1
         * set1.size()=2
         * set2.size()=2
         * 8088 被客户端连接了!
         *
         * 打印这条信息证明是连接8088服务器时，重复消费的情况发生，
         * 将7077关联的selectionKey对应的SocketChannel通道取出来
         * 但是值为null, socketChannel == null.
         *出现本现象的原因：
         * 造成这样的原因是变量set2 在每一次循环中使用的是底层提供的同一个对象，一直在往set2 里面
         * 添加已就绪的SelectionKey ，一个是关联7777 端口的SelectionKey ，另一个是关联8888 端
         * 口的SelectionKey 。在这期间，从未从set2 中删除Se lectionKey ，因此， set2 的s ize 值为2,
         * 再使用wbile(iterator. basNext（））对set2 循环两次，就导致了重复消费。解决重复消费问题的
         * 方法就是使用remove（）方法删除set2 中处理过后的SelectionKey 。
         */
    }
}
