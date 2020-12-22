package com.itdom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 *DatagramPacket 类中的p ublic sy nchronized void setData(byte[] but) 方法的作用是为此包
 * 设置数据缓冲区。将此DatagramPacket 的偏移量设置为0 ，长度设置为buf 的长度。
 * DatagramPacket 类中的publi c sync hronized void setData(b yte[] b时， int o的时， int length)
 * 方法的作用是为此包设置数据缓冲区。此方法设置包的数据、长度和偏移量。
 * DatagramPacket 类中的p ublic synchronized int getOffset （）方法的作用是返回将要发送或
 * 接收到的数据的偏移量。
 * DatagramPacket 类中的public synchronized void setLength(int length ）方法的作用是为此
 * 包设置长度。包的长度是指包数据缓冲区中将要发送的字节数，或用来接收数据的包数据缓
 * 冲区的字节数。长度必须小于或等于偏移量与包缓冲区氏度之和。
 */
public class UDPSetDataMethodTest2 {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.connect(new InetSocketAddress("localhost",8088));
        String newString="我是员工";
        byte[] byteArray = newString.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(new byte[]{}, 0);
        datagramPacket.setData(byteArray);
        datagramPacket.setLength(2);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
