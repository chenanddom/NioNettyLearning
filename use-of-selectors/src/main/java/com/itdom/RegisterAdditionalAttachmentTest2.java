package com.itdom;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * SelectableChannel 类中的public final SelectionKey register(Selector sel, int ops, Object
 * att ） 方法的作用是向给定的选择器注册此通道，返回一个选择键。如果当前已向给定的选择
 * 器注册了此通道，则返回表示该注册的选择键。该键的相关操作集将更改为ops ， 就像调用
 * in terestOps( int）方法一样。如果att 参数不为n ull ，则将该键的附件设置为该值。如果已取
 * 消该键， 则抛出CancelledKeyException 异常。如果尚未向给定的选择器注册此通道， 则注
 * 册该通道并返回得到的新键。该键的初始可用操作集是ops ， 并且其附件是att 。可在任意时
 * 间调用此方法。如果调用此方法的同时正在进行另一个此方法或config ureBlocking（）方法的
 * 调用，则在另一个操作完成前将首先阻塞该调用。然后，此方法将在选择器的键集上实现同
 * 步。因此，如果调用此方法时并发地调用了涉及同一选择器的另一个注册或选择操作， 则
 * 可能阻塞此方法的调用。如果正在进行此操作时关闭了此通道，则此方法返回的键是已取
 * 消的，因此返回键无效。参数sel 代表要向其注册此通道的选择器， ops 代表所得键的可用
 * 操作集， att 代表所得键的附件， a忧r 参数可能为null 。返回值表示此通道向给定选择器注册
 * 的键。
 * SelectionKey 类中的p ublic final O同ect attachment （） 方法的作用是获取当前的附加对象。
 * 返回值代表当前已附加到此键的对象，如果没有附加对象，则返回null 。
 */
public class RegisterAdditionalAttachmentTest2 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost",8088));
        while (true){
            int keyCount = selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if (key.isConnectable()){
                    System.out.println("client.isConnectable()");
                    if (socketChannel.isConnectionPending()){
                        while (!socketChannel.finishConnect()){
                            System.out.println("!socketChannel.finishConnect()......");
                        }
                        socketChannel.register(selector,SelectionKey.OP_WRITE,"我使用附件进行注册，我来自客户端， 你好服务端！");
                    }
                }
                if (key.isWritable()){
                    ByteBuffer byteBuffer = ByteBuffer.wrap(((String)key.attachment()).getBytes());
                    socketChannel.write(byteBuffer);
                    socketChannel.close();
                }
                iterator.remove();
                System.out.println("client end");
            }
        }
        /**先执行RegisterAdditionalAttachmentTest再执行RegisterAdditionalAttachmentTest2
         * 执行结果:
         * client.isConnectable()
         * client end
         * client end
         */
    }
}
