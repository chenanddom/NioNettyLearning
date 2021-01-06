package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * public abstract void cancel()方法的作用是请求取消此键的通道到其选择器的注册。一旦
 * 返回，该键就是无效的， 并且将被添加到其选择器的已取消键集中。在进行下一次选择操作
 * 时， 将从所有选择器的键集中移除该键。如果已取消了此键，则调用此方法元效。一旦取消
 * 某个键， SelectionKey.选择器的已取消键集保持同步，因此，如果通过涉及同一选择器的
 * 取消或选择操作并发调用它， 则它可能会暂时受阻塞。
 */
public class SelectionKeyCanccelTest {
    private static Set<SelectionKey> selectionKeySet;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8088));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        SocketChannel socketChannel=null;
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("cancel() after selector.keys().size()="+selector.keys().size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        while (true){
            selector.select();
            selectionKeySet = selector.selectedKeys();
            System.out.println("cancel() before selection.keys().size()="+selector.keys().size());
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    socketChannel = channel.accept();
                }
                key.cancel();
            }
        }
    }
}
