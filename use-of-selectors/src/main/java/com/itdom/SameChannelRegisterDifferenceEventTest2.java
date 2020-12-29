package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 对相同的通道注册不同的相关事件返回同一个Selectio
 */
public class SameChannelRegisterDifferenceEventTest2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 8888);
        socket.getOutputStream().write("1234567890".getBytes());
        socket.close();
    }

}
