package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * public abstract Selector selector（）方法的作用是返回SelectionKey 关联的选择器。即使
 * 已取消该键， 此方法仍将继续返回选择器。
 */
public class AssociatedWithSelectorkey {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8888));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        Selector selector2 = selectionKey.selector();
        System.out.println("selector1"+" "+selector.hashCode());
        System.out.println("selector2"+" "+selector2.hashCode());
    }
}
