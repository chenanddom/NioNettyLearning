package com.itdom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 理论上，一个UDP 包最大的长度为21 6 - 1 ( 65536 一1 = 65535 ），因此， IP 包最大的发
 * 送长度为65 535 。但是，在这65535 之内包含IP 协议头的20 个字节，还有UDP 协议头的
 * 8 个字节，即65535 一20 - 8 = 65507 ，因此， UDP 传输用户数据最大的长度为65507 。如果
 * 传输的数据大于65507 ，则在发送端出现异常。
 */
public class UDPMaximumPacketTest2 {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.connect(new InetSocketAddress("localhost",8088));
        String sendString="";
        //如果超过了65507就会报错
        for (int i = 0; i < 65504; i++) {
            sendString = sendString+"a";
        }
        sendString=sendString+"end";
        /**
         * 此处再添加数据就会超出范围就会报错
         * Exception in thread "main" java.net.SocketException: The message is larger than the maximum supported by the underlying transport: Datagram send failed
         * 	at java.net.DualStackPlainDatagramSocketImpl.socketSend(Native Method)
         * 	at java.net.DualStackPlainDatagramSocketImpl.send(DualStackPlainDatagramSocketImpl.java:136)
         * 	at java.net.DatagramSocket.send(DatagramSocket.java:693)
         * 	at com.itdom.UDPMaximumPacketTest2.main(UDPMaximumPacketTest2.java:27)
          */
        sendString= sendString+"test";

        DatagramPacket datagramPacket = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
