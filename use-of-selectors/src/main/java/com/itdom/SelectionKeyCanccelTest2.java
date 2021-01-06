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
 * public abstract void cancel()方法的作用是请求取消此键的通道到其选择器的注册。一旦
 * 返回，该键就是无效的， 并且将被添加到其选择器的已取消键集中。在进行下一次选择操作
 * 时， 将从所有选择器的键集中移除该键。如果已取消了此键，则调用此方法元效。一旦取消
 * 某个键， SelectionKey.选择器的已取消键集保持同步，因此，如果通过涉及同一选择器的
 * 取消或选择操作并发调用它， 则它可能会暂时受阻塞。
 */
public class SelectionKeyCanccelTest2 {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost",8088));
        socketChannel.close();
    }
}
