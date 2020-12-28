package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * int s elector. select()方法返回值的含义是己更新其准备就绪操作集的键的数目，该数目
 * 可能为零或排零， 非零的情况就是向set2 中添加SelectionKey的个数， 值为零的情况是
 * set2 中的元素并没有更改。
 */
public class SelectMethodReturnValueMeaningTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",7777));
        serverSocketChannel.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.bind(new InetSocketAddress("localhost",8888));
        serverSocketChannel2.configureBlocking(false);


        ServerSocketChannel serverSocketChannel3 = ServerSocketChannel.open();
        serverSocketChannel3.bind(new InetSocketAddress("localhost",9999));
        serverSocketChannel3.configureBlocking(false);

        Selector selector = Selector.open();


        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey selectionKey2 = serverSocketChannel2.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey selectionKey3 = serverSocketChannel3.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int keyCount = selector.select();
            Set<SelectionKey> set1 = selector.keys();
            Set<SelectionKey> set2 = selector.selectedKeys();
            System.out.println("keyCount="+keyCount);
            System.out.println("set1 size="+set1.size());
            System.out.println("set2 size="+set2.size());
            Iterator<SelectionKey> iterator = set2.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) key.channel();
                serverSocketChannel.accept();
            }
            Thread.sleep(10000);
        }

        /**
         * 执行结果
         * keyCount=1
         * set1 size=3
         * set2 size=1
         * keyCount=2
         * set1 size=3
         * set2 size=3
         * keyCount=0
         * set1 size=3
         * set2 size=3
         */

    }

}

