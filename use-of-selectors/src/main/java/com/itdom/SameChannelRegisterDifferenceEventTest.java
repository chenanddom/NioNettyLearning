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
 * 对相同的通道注册不同的相关事件返回同一个Selectio
 */
public class SameChannelRegisterDifferenceEventTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",8888));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            int keyCount = selector.select();
            Set<SelectionKey> set1 = selector.keys();
            Set<SelectionKey> set2 = selector.selectedKeys();
            System.out.println("keyCountA="+keyCount);
            System.out.println("set1 size="+set1.size());
            System.out.println("set2 size="+set2.size());
            Iterator<SelectionKey> iterator = set2.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();

                ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel)key.channel();
                SocketChannel socketChanne2 = serverSocketChannel1.accept();
                socketChanne2.configureBlocking(false);
                SelectionKey key2 = socketChanne2.register(selector, SelectionKey.OP_READ);
                System.out.println("key2.isReadable()="+((SelectionKey.OP_READ & ~key2.interestOps())==0));
                System.out.println("key2.isWritable()="+((SelectionKey.OP_WRITE & ~key2.interestOps())==0));


                SelectionKey key3 = socketChanne2.register(selector, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                System.out.println("key3.isReadable()="+((SelectionKey.OP_READ & ~key3.interestOps())==0));
                System.out.println("key3.isWritable()="+((SelectionKey.OP_WRITE & ~key3.interestOps())==0));

                System.out.println("keyCountB="+keyCount);
                System.out.println("set1 size="+set1.size());
                System.out.println("set2 size="+set2.size());

                System.out.println("key2==key3结果："+(key2==key3));
                System.out.println("key==key3结果："+(selectionKey==key3));
            }
            Thread.sleep(Integer.MAX_VALUE);

            /**
             * 先执行SameChannelRegisterDifferenceEventTest
             * 再执行SameChannelRegisterDifferenceEventTest2
             * 执行结果：
             * keyCountA=1
             * set1 size=1
             * set2 size=1
             * key2.isReadable()=true
             * key2.isWritable()=false
             * key3.isReadable()=true
             * key3.isWritable()=true
             * keyCountB=1
             * set1 size=2
             * set2 size=1
             * key2==key3结果：true
             */
        }
    }

}
