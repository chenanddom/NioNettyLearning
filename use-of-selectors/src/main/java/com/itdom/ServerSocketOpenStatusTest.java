package com.itdom;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

/**
 * public final boolean isOpen（）方法的作用是判断此通道是否处于打开状态。
 */
public class ServerSocketOpenStatusTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        System.out.println("A serverSocketChannel.isOpen()="+serverSocketChannel.isOpen());
        serverSocketChannel.close();
        System.out.println("B serverSocketChannel.isOpen()="+serverSocketChannel.isOpen());
        serverSocketChannel = ServerSocketChannel.open();
        System.out.println("C serverSocketChannel.isOpen()="+serverSocketChannel.isOpen());
        serverSocketChannel.close();

        /**
         * 程序执行结果:
         * A serverSocketChannel.isOpen()=true
         * B serverSocketChannel.isOpen()=false
         * C serverSocketChannel.isOpen()=true
         */
    }
}
