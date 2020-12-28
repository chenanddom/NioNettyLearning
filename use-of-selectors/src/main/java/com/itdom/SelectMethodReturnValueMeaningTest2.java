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
 * int s elector. select（） 方法返回值的含义是己更新其准备就绪操作集的键的数目，该数目
 * 可能为零或排零， 非零的情况就是向set2 中添加SelectionK町的个数， 值为零的情况是
 * set2 中的元素并没有更改。
 */
public class SelectMethodReturnValueMeaningTest2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 7777);
        socket.close();
    }

}

