package com.itdom;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
public class LingerOptionTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        System.out.println("A socket.getSoLinger()="+socket.getSoLinger());
        //服务端将会丢弃缓冲区中的数据
//        socket.setSoLinger(true,0);
        //在on=false 时， close（）万法立即返回并且数据不丢失，正常进行4 次“挥手”
        socket.setSoLinger(false,500000);
        System.out.println("B socket.getSoLinger()="+socket.getSoLinger());
        OutputStream outputStream = socket.getOutputStream();
        for (int i = 0; i < 10; i++) {
            outputStream.write(("1273891273981273891273198237819273891273981273891" +
                    "2738192378912312371289371298731829738912378129312312312").getBytes());
        }
        outputStream.write("end!".getBytes());
        System.out.println("socket close before="+System.currentTimeMillis());
        outputStream.close();
        socket.close();
        System.out.println("socket close after="+System.currentTimeMillis());
        serverSocket.close();
    }
}
