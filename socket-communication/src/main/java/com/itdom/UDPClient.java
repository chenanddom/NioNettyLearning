package com.itdom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.connect(new InetSocketAddress("localhost",8088));
        String newString = "1234567890";
        byte[] byteArray = newString.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(byteArray, byteArray.length);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
