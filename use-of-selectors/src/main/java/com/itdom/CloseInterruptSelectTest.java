package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *阻塞在select （）或select (long ）方法中的线程通过选择器的close （）方法被中断
 */
public class CloseInterruptSelectTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",7777));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    selector.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        while (true){
            System.out.println("begin "+System.currentTimeMillis());
            int keyCount = selector.select();
            System.out.println(" end "+System.currentTimeMillis());
            Set<SelectionKey> keys = selector.keys();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                    ServerSocket serverSocket = socketChannel.socket();
                    Socket socket = serverSocket.accept();
                    socket.close();
                }
            }
        }
        /**
         * 执行结果:
         * begin 1609210719817
         *  end 1609210721827
         * Exception in thread "main" java.nio.channels.ClosedSelectorException
         * 	at sun.nio.ch.SelectorImpl.keys(SelectorImpl.java:68)
         * 	at com.itdom.CloseInterruptSelectTest.main(CloseInterruptSelectTest.java:42)
         */
    }
}
