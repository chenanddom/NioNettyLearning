package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * select（）方法不阻塞的原因和解决办法
 */
public class SelectMethodNotBlockTest2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8088);
        socket.close();
    }
}
