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
 * 对通道执行close()方法后的效果
 * 关闭某个键的通道，通道对应的键都被添加到其选择器的已取消键集中，会导致在下
 * 一次select()方法选择操作期间注销该键的通道，而在注销时将从所有选择器的键集中移除
 * 该键。
 *
 */
public class CloseChannelTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",7777));
        serverSocketChannel.configureBlocking(false);

        ServerSocketChannel serverSocketChannel2 = ServerSocketChannel.open();
        serverSocketChannel2.bind(new InetSocketAddress("localhost",8888));
        serverSocketChannel2.configureBlocking(false);


        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey selectionKey2 = serverSocketChannel2.register(selector, SelectionKey.OP_ACCEPT);

        new Thread(){
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("localhost", 7777);
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("我是中国人，我来自客户端 to7777!".getBytes());
                    socket.close();

                    Socket socket2 = new Socket("localhost", 8888);
                    OutputStream outputStream2 = socket2.getOutputStream();
                    outputStream2.write("我是中国人，我来自客户端 to8888!".getBytes());
                    socket2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    System.out.println();
                    Set<SelectionKey> keys = selector.keys();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    System.out.println("channel.close()之后的信息");
                    System.out.println("keys.size()="+keys.size());
                    System.out.println("selectedKeys.size()="+selectionKeys.size());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        Thread.sleep(1000);

        while (true){
            int keyCount = selector.select();
            Set<SelectionKey> keys = selector.keys();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("channel.close()之前的信息:");
            System.out.println("keys.size()="+keys.size());
            System.out.println("selectedKeys.size()="+selectionKeys.size());
            System.out.println();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isAcceptable()){
                    ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
                    ServerSocket serverSocket = socketChannel.socket();
                    Socket socket = serverSocket.accept();
                    InputStream inputStream = socket.getInputStream();
                    byte[] byteArray = new byte[1024];
                    int readLength = inputStream.read(byteArray);
                    while (readLength!=-1){
                        String newString = new String(byteArray, 0, readLength);
                        System.out.println(newString);
                        readLength = inputStream.read(byteArray);
                    }
                    inputStream.close();
                    socket.close();
                    if (serverSocket.getLocalPort()==7777){
                        socketChannel.close();
                    }
                }
            }
        }
        /**
         * 执行程序：
         * 结果：
         * channel.close()之前的信息:
         * keys.size()=2
         * selectedKeys.size()=2
         *
         * 我是中国人，我来自客户端 to7777!
         * 我是中国人，我来自客户端 to8888!
         *
         * channel.close()之后的信息
         * keys.size()=1
         * selectedKeys.size()=1
         */
    }
}
