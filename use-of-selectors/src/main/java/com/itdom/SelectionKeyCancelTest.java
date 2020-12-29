package com.itdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 1. 对SelectionKey 执行cancel()方法后的效果
 * 调用该键的cancel()方法来取消键，该键都被添加到其选择器的已取消键集中。取消某
 * 个键会导致在下一次select() 方法选择操作期间注销该键的通道，而在注销时将从所有选择
 * 器的键集中移除该键。
 */
public class SelectionKeyCancelTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 7777));
        serverSocketChannel.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.bind(new InetSocketAddress("localhost", 8888));
        serverSocketChannel2.configureBlocking(false);

        Selector selector = Selector.open();

        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        SelectionKey selectionKey2 = serverSocketChannel2.register(selector, SelectionKey.OP_ACCEPT);

        new Thread() {
            @Override
            public void run() {

                try {
                    Socket socket = new Socket("localhost", 7777);
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("我是中国人，我来字客户端 to7777".getBytes());
                    socket.close();

                    Socket socket2 = new Socket("localhost", 8888);
                    OutputStream outputStream2 = socket2.getOutputStream();
                    outputStream2.write("我是中国人，我来字客户端 to8888".getBytes());
                    socket2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    System.out.println();
                    Set<SelectionKey> keys = selector.keys();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    System.out.println("select()方法执行第二次后的信息:");
                    System.out.println("keys.size()=" + keys.size());
                    System.out.println("selectionKeys.size()=" + selectionKeys.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        while (true) {
            int keyCount = selector.select();
            Set<SelectionKey> keys = selector.keys();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("取消之前的消息:");
            System.out.println("keys.size()=" + keys.size());
            System.out.println("selectionKeys.size()=" + selectionKeys.size());
            System.out.println();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    ServerSocket serverSocket = channel.socket();
                    Socket socket = serverSocket.accept();
                    InputStream inputStream = socket.getInputStream();
                    byte[] byteArray = new byte[1024];
                    int readLength = inputStream.read(byteArray);
                    while (readLength != -1) {
                        String newString = new String(byteArray, 0, readLength);
                        System.out.println(newString);
                        readLength = inputStream.read(byteArray);
                    }
                    inputStream.close();
                    socket.close();
                    if (serverSocket.getLocalPort() == 7777) {
                        key.cancel();
                        System.out.println("取消之后的信息:");
                        System.out.println("keys.size()=" + keys.size());
                        System.out.println("selectionKeys.size()=" + selectionKeys.size());
                    }
                }
            }
        }
        /**
         * 执行结果：
         * 取消之前的消息:
         * keys.size()=2
         * selectionKeys.size()=1
         *
         * 我是中国人，我来字客户端 to7777
         * 取消之后的信息:
         * keys.size()=2
         * selectionKeys.size()=1
         * 取消之前的消息:
         * keys.size()=1
         * selectionKeys.size()=1
         *
         * 我是中国人，我来字客户端 to8888
         *
         * select()方法执行第二次后的信息:
         * keys.size()=1
         * selectionKeys.size()=1
         *
         * 对SelectionKey 执行cancel()方法操作会将SelectionKey 放入取消键集中，并且在下一
         * 次执行select()方法时删除这个SelectionKey 所有的键集，并且通道被注销，因此，在控制
         * 台的最后输出两个l1:
         */
    }
}
