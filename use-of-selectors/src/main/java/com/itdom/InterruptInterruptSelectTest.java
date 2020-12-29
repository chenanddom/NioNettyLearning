package com.itdom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *阻塞在select （）或select (long ）方法中的线程调用in t er r upt （） 方法被中断
 */
public class InterruptInterruptSelectTest {
    private static Thread mainThread = Thread.currentThread();
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost",7777));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        new Thread(){
            @Override
            public void run() {
                try {
                  Thread.sleep(2000);
                  /*
                 interrupt （ ）含义是不想让线程工作了，也就是要销毁线程
                 interrupt （）方法只是对线程对象打一个标记，
                代表这个线程要销毁， 因此，要结合interrupted （）进行判断，
                如果结果为true, 以 break 退出while(true ），结束当前线程的执行
                   */
                  //中断住线程
                    mainThread.interrupt();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        while (true){
            System.out.println("begin "+System.currentTimeMillis());
            int keyCount = selector.select();
//            if (mainThread.isInterrupted()){
//                break;
//            }
            mainThread.interrupted();//该方法用于清除中断的标识
            System.out.println(" end "+System.currentTimeMillis());
            Set<SelectionKey> keys = selector.keys();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()){
                    ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
                    ServerSocket serverSocket = socketChannel.socket();
                    Socket socket = serverSocket.accept();
                    socket.close();
                }
            }
        }
//        serverSocketChannel.close();
        /**
         * 执行结果:
         *begin 1609214138151
         *  end 1609214140152
         * begin 1609214140153
         */
    }
}
