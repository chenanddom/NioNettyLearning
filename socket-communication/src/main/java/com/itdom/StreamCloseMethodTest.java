package com.itdom;


import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 调用Stream 的close （）方法造成Socket 关闭
 */
public class StreamCloseMethodTest {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        int length = inputStream.available();
        byte[] charArray = new byte[length];
        int readLength = inputStream.read(charArray);
        while (readLength!=-1){
            String newString = new String(charArray,0,readLength);
            System.out.print(newString+" "+System.currentTimeMillis());
            readLength = inputStream.read(charArray);
        }
        inputStream.close();
        socket.close();
        serverSocket.close();
    }

}
