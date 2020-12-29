package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 调用Selector. close()万法删除全部键并且通道注销
 */
public class SelectorCloseTest {
    private static Selector selector;
    public static void main(String[] args) throws IOException {
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    selector.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8888));
        serverSocketChannel.configureBlocking(false);

        selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        int keyCount = selector.select();

        Set<SelectionKey> selectedKeySet = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectedKeySet.iterator();
        while (iterator.hasNext()){
            SelectionKey key = iterator.next();
            if (key.isAcceptable()){
                ServerSocketChannel socketChannel = (ServerSocketChannel)key.channel();
                Socket socket = socketChannel.socket().accept();
            }
            iterator.remove();
        }
        serverSocketChannel.close();
        System.out.println("main end");
        /**
         * 执行结果
         * Exception in thread "main" java.nio.channels.ClosedSelectorException
         * 	at sun.nio.ch.SelectorImpl.selectedKeys(SelectorImpl.java:74)
         * 	at com.itdom.SelectorCloseTest.main(SelectorCloseTest.java:40)
         * 	原因：因为执行Selector 的close（）方法后，除了再次调用close（）和wakeup（）方法外，调用
         * S巳lector 的其他方法均出现异常。
         */
    }
}
