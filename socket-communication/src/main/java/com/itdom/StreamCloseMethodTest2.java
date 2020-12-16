package com.itdom;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 调用Stream 的close （）方法造成Socket 关闭
 */
public class StreamCloseMethodTest2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 8088);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("我是中国人".getBytes());
        /**
         * close()的源码：
         *  public void close() throws IOException {
         *         // Prevent recursion. See BugId 4484411
         *         if (closing)
         *             return;
         *         closing = true;
         *         if (socket != null) {
         *             if (!socket.isClosed())
         *                 socket.close();
         *         } else
         *             impl.close();
         *         closing = false;
         *     }
         *     从上述源代码可知，当调用java.net.SocketinputStream 类的close（）方法时， 顺便也将
         * Socket （套接字） close（）关闭。如果Socket 关闭，则服务端与客户端不能进行通信。因此，
         * 当执行代码OutputStream outputStream = socket.getOutputStream（）取得输出流时，就会出现
         * 异常。
         */
        outputStream.close();
        Thread.sleep(Integer.MAX_VALUE);
    }

}
