package com.itdom;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class EnableAndMaskNagleAlgorithmTest2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.10.54", 8088);
        socket.setTcpNoDelay(false);
        InputStream inputStream = socket.getInputStream();
        long beginTime = System.currentTimeMillis();
        byte[] byteArray = new byte[1];
        int readLength = inputStream.read(byteArray);
        while (readLength!=-1){
            String newString = new String(byteArray, 0, readLength);
            System.out.println(newString);
            readLength = inputStream.read(byteArray);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-beginTime);
        socket.close();
    }
}
