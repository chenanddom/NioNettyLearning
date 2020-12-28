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
 * 如果两个不同的通道注册到相同的选择器，那么极易出现重复消费的情况。
 */
public class RepeatedConsumptionTest2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7077);
        socket.close();
    }
}
