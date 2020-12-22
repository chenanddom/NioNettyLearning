package com.itdom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *IP 规定了以下4 种服务类型，用来定性地描述服务的质量。
 * 1 ) IPTOS LOWCOST ( Ox02 ） ： 发送成本低。
 * 2 ) IPTOS R ELIAB ILITY ( Ox 0 4 ） ： 高可靠性，保证把数据可靠地送到目的地。
 * 3 ) IPTOS THROUGHPUT ( Ox08 ） ： 最高吞吐量， 一次可以接收或者发送大批量的数据。
 * 4) IPTOS LOWDELAY ( OxlO ） ： 最小延迟，传输数据的速度’快，把数据快速送达目的地。
 * 这4 种服务类型还可以使用“或”运算进行相应的组合。
 * public void setTrafficClass(int tc）方法的作用是为从此Socket 上发送的包在IP 头中设置
 * 流量类别（traffic class ）。
 * publi c int getTrafficC lass（）方法的作用是为从此Socket 上发送的包获取IP 头中的流量
 * 类别或服务类型。
 * 当向IP 头中设置了流量类型后， 路由器或交换机就会根据这个流量类型来进行不同的
 * 处理， 同时必须要硬件设备进行参与处理。
 */
public class SocketTrafficClassTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        Socket socket = serverSocket.accept();
        socket.close();
        serverSocket.close();
    }
}
