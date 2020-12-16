package com.itdom;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * pub lie j ava. util.List<InterfaceAddress> getinterfaceAddresses （）方法的作用： 获取网络接
 * 口的InterfaceAddresses 列表。通过使用InterfaceAddr巳sses 类中的方法可以取得网络接口对
 * 应的IP 地址、子网掩码和广播地址等相关信息。对于1Pv4 地址， 可以取得IP 地址、子网
 * 掩码和广播地址，而对于IPv6 地址，可以取得IP 地址和网络前缀长度这样的信息。
 * 什么是网络前缀长度？ 网络前缀长度在IPv4 地址上下文中也称为子网掩码。典型的
 * 1Pv4 值是8 ( 255.0 . 0.0 ）、16 ( 255.255.0.0 ）或24 ( 255.255.255.0 ） ；典型的IPv6 值是128
 * (:: 1/128 ）或10 ( fe80: :2 03:baff：“27: 1243 /1 0 ） 。
 * 前面介绍过InetAddress 类是对应IP 地址信息的，而InterfaceAddress 类是对应网络接
 * 口信息的，可以在InterfaceAddress 对象中取得IP 地址的lnetAddress 对象信息，以及多播
 * 地址的InetAddress 对象信息，还有子网掩码等。
 * lnetAddress getAddress()/lnetAdd 「ess getB「oadcast()/sho同getNetworkPrefixlength()
 * 方法的使用
 * public lnetAddress getAddress （）方法的作用：返回此Interfa ceAddress 的InetAddress 。
 * public lnetAddress getBroadcast（）方法的作用：返回此InterfaceAddress 广播地址的
 * InetAddress 。由于只有IPv4 网络具有广播地址，因此对于IPv6 网络将返回null 。
 * public short getNetworkPrefixLength （） 方法的作用：返回此InterfaceAddress 的网络前缀
 * 长度。
 */
public class InterfaceAddressTest {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println(" getName()获取网络设备名称="+networkInterface.getName());
            System.out.println(" getDisplayName()获取网络设备显示名称="+networkInterface.getDisplayName());
            List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
            for (int i = 0; i < interfaceAddresses.size(); i++) {
                InterfaceAddress interfaceAddress = interfaceAddresses.get(i);
                InetAddress address = interfaceAddress.getAddress();
                if (address!=null){
                    System.out.println(" interfaceAddress.getAddress()="+address.getHostAddress());
                }
                System.out.println(" getNetworkPrefixLength()="+interfaceAddress.getNetworkPrefixLength());
                System.out.println();
            }
            System.out.println();
        }
    }
}
