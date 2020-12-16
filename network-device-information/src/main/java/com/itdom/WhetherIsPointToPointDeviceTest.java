package com.itdom;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 *3.1.7 判断是否为点对点设备
 * public boolean isPointToPoint（）方法的作用：判断当前的网络设备是不是点对点设备。什
 * 么是point to point （点对点）？它被设计的主要目的就是用来通过拨号或专线方式建立点对点
 * 连接以发送数据，使其成为各种主机、网桥和路由器之间简单连接的一种通信解决方案。
 */
public class WhetherIsPointToPointDeviceTest {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println(" getName获取网络设备的名称="+networkInterface.getName());
            System.out.println(" getDisplayName获取网络设备的显示名称="+networkInterface.getDisplayName());
            System.out.println(" isPointToPoint是不是点对点的设备="+networkInterface.isPointToPoint());
            System.out.println();
            System.out.println();
        }
    }
}
