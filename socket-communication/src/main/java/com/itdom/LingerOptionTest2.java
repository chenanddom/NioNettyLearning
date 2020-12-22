package com.itdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLSyntaxErrorException;

/**
 * Socket 中的SO_LINGER 选项用来控制Socket 关闭close（）方法时的行为。在默认情况
 * 下，执行Socket 的close （）方法后，该方法会立即返回，但底层的Socket 实际上并不会立即
 * 关闭，它会延迟一段时间。在延迟的时间里做什么呢？是将“发送缓冲区”中的剩余数据在
 * 延迟的时间内继续发送给对方，然后才会真正地关闭Socket 连接。
 * public void setSoLinger(boolean on, int linger）方法的作用是启用／禁用具有指定逗留时
 * 间（以秒为单位）的SO LINGER 。最大超时值是特定于平台的。该设置仅影响套接字关闭。
 * 参数on 的含义为是否逗留，参数linger 的含义为逗留时间，单位为秒。
 * public int getSoLinger （）方法的作用是返回SO_LINGER 的设置。返回－ 1 意味着禁用该
 * 选项。该设置仅影响套接字关闭。返回值代表SO LINGER 的设置。
 * 从public void setSoLinger(boolean on, int linger）方法的源代码中可以发现以下几点内容。
 * public void setSoLinger(boolean on, int linger)的源码如下：
 *  public void setSoLinger(boolean on, int linger) throws SocketException {
 *         if (isClosed())
 *             throw new SocketException("Socket is closed");
 *         if (!on) {
 *             getImpl().setOption(SocketOptions.SO_LINGER, new Boolean(on));
 *         } else {
 *             if (linger < 0) {
 *                 throw new IllegalArgumentException("invalid value for SO_LINGER");
 *             }
 *             if (linger > 65535)
 *                 linger = 65535;
 *             getImpl().setOption(SocketOptions.SO_LINGER, new Integer(linger));
 *         }
 *     }
 * 1) on 传入false, SO LINGER 功能被屏蔽，因为对代码语句
 * getimpl() . setOptio口（ SocketOptions.SO_ LINGER , new Boolean(on));
 * 中的new Boolean（） 传入了false 值。对参数on 传入false 值是close（）方法的默认行为， 也
 * 就是close（）方法立即返回，但底层Socket 并不关闭，直到发送完缓冲区中的剩余数据，才
 * 会真正地关闭Socket 的连接。
 */
public class LingerOptionTest2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        /**
         * 设置小的接受缓冲区
         * 目的是先让server服务发送端close()
         * 然后将服务端发送缓冲区钟的数据在传入客户端的接受缓冲区钟
         * 虽然服务端的socket.close()已经执行了但是数据不会丢失
         */
        socket.setReceiveBufferSize(1);
        socket.bind(new InetSocketAddress("localhost",7077));
        socket.connect(new InetSocketAddress("localhost",8088));
        InputStream inputStream = socket.getInputStream();
        byte[] byteArray = new byte[1];
        int readLength = inputStream.read(byteArray);
        while (readLength!=-1){
            System.out.println(new String(byteArray,0,readLength));
            readLength = inputStream.read(byteArray);
        }
        System.out.println("client read end time="+ System.currentTimeMillis());
        inputStream.close();
        socket.close();
    }
}
