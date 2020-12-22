package com.itdom;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 * 理论上，一个UDP 包最大的长度为21 6 - 1 ( 65536 一1 = 65535 ），因此， IP 包最大的发
 * 送长度为65 535 。但是，在这65535 之内包含IP 协议头的20 个字节，还有UDP 协议头的
 * 8 个字节，即65535 一20 - 8 = 65507 ，因此， UDP 传输用户数据最大的长度为65507 。如果
 * 传输的数据大于65507 ，则在发送端出现异常。
 */
public class UDPMaximumPacketTest {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8088);
        byte[] byteArray = new byte[65507];
        DatagramPacket datagramPacket = new DatagramPacket(byteArray, byteArray.length);
        datagramSocket.receive(datagramPacket);
        datagramSocket.close();
        System.out.println("服务端接受到的数据长度为："+datagramPacket.getLength());
        String getString = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
        FileOutputStream fileOutputStream = new FileOutputStream("./socket-communication/file/UDPMaximumPacketTest.txt");
        fileOutputStream.write(getString.getBytes());
        fileOutputStream.close();
    }
}
