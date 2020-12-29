package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * 删除键集中的键会导致UnsupportedOperationException 异常
 * 测试用的代码如下：
 * public
 */
public class UnsupportedOperationExceptionTest {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",7777));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selector.keys().remove(selectionKey);
        /**
         * 执行结果：
         * Exception in thread "main" java.lang.UnsupportedOperationException
         * 	at java.util.Collections$UnmodifiableCollection.remove(Collections.java:1058)
         * 	at com.itdom.UnsupportedOperationExceptionTest.main(UnsupportedOperationExceptionTest.java:22)
         */
    }
}
