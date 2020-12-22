package com.itdom;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *IP 规定了以下4 种服务类型，用来定性地描述服务的质量。
 * 1 ) IPTOS LOWCOST (Ox02) ： 发送成本低。
 * 2 ) IPTOS R ELIAB ILITY (Ox04) ： 高可靠性，保证把数据可靠地送到目的地。
 * 3 ) IPTOS THROUGHPUT (Ox08) ： 最高吞吐量， 一次可以接收或者发送大批量的数据。
 * 4) IPTOS LOWDELAY (Ox1O) ： 最小延迟，传输数据的速度’快，把数据快速送达目的地。
 * 这4 种服务类型还可以使用“或”运算进行相应的组合。
 * public void setTrafficClass(int tc）方法的作用是为从此Socket 上发送的包在IP 头中设置
 * 流量类别（traffic class ）。
 * publi c int getTrafficC lass（）方法的作用是为从此Socket 上发送的包获取IP 头中的流量
 * 类别或服务类型。
 * 当向IP 头中设置了流量类型后， 路由器或交换机就会根据这个流量类型来进行不同的
 * 处理， 同时必须要硬件设备进行参与处理。
 */
public class SocketTrafficClassTest2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.setTrafficClass(0x02);
        socket.connect(new InetSocketAddress("localhost",8088));
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("我是发送的数据".getBytes());
        outputStream.close();
        socket.close();
    }
}
