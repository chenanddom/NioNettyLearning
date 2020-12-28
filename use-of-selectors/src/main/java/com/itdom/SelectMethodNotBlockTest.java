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
 * select（）方法不阻塞的原因和解决办法
 */
public class SelectMethodNotBlockTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8088));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            int ketCount = selector.select();
            Set<SelectionKey> allKeySet = selector.keys();
            Set<SelectionKey> readySelectionKeys = selector.selectedKeys();
            System.out.println("readySelectionKeysCount="+ketCount);
            System.out.println("allKeySet siz=e"+allKeySet.size());
            System.out.println("readySelectionKeySet siz=e"+readySelectionKeys.size());
            /**
             * 此处如果不执行会出现死循环
             */
            Iterator<SelectionKey> iterator = readySelectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey1 = iterator.next();
                ServerSocketChannel channel = (ServerSocketChannel)selectionKey1.channel();
                //使用方法accept （）将事件处理掉
                channel.accept();
            }
        }
    }
}
