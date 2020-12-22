package com.itdom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8088);
        byte[] byteArray = new byte[12];
        /**
         * 构造方法第二个参数也要协商10，代表了接受的数据的长度的为10
         * 和客户端发送数据的长度要一致
         */
        DatagramPacket datagramPacket = new DatagramPacket(byteArray, 10);
        datagramSocket.receive(datagramPacket);
        datagramSocket.close();
        System.out.println("包中数据的长度:"+datagramPacket.getLength());
        System.out.println(new String(datagramPacket.getData(),0,datagramPacket.getLength()));
    }
}
