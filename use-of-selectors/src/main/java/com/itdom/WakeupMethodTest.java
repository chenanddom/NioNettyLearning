package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * public abstract Selector wakeup()方法的作用是使尚未返回的第一个选择操作立即返回。
 * 如果另一个线程目前正阻塞在select（） 或select(long） 方法的调用中， 则该调用将立即返回。
 * 如果当前未进行选择操作，那么在没有同时调用selectNow()方法的情况下，对上述方法的
 * 下一次调用将立即返回。在任一情况下，该调用返回的值可能是非零的。如果未同时再次调
 * 用此方法，则照常阻塞select() 或select(long）方法的后续调用。在两个连续的选择操作之间
 * 多次调用此方法与只调用一次的效果相同。
 */
public class WakeupMethodTest {
    private static Selector selector;

    public static void main(String[] args) throws IOException {
        new Thread() {
            @Override
            public void run() {
                try {

                    Thread.sleep(10000);
                    selector.wakeup();
                    Set<SelectionKey> set1 = selector.keys();
                    Set<SelectionKey> set2 = selector.selectedKeys();
                    System.out.println("执行wakeup()方法之后的selector的信息");
                    System.out.println("set1.size()=" + set1.size());
                    System.out.println("set2.size()=" + set2.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel.configureBlocking(false);

        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        int keyCount = selector.select();
        Set<SelectionKey> selectionKeySet = selector.selectedKeys();

        Iterator<SelectionKey> iterator = selectionKeySet.iterator();
        while (iterator.hasNext()) {
            SelectionKey selectionKey = iterator.next();
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel channel = socketChannel.accept();
                channel.close();
            }
            iterator.remove();
        }
        /**
         * 执行结果：
         * 执行wakeup()方法之后的selector的信息
         * set1.size()=1
         * set2.size()=0
         */
    }
}
