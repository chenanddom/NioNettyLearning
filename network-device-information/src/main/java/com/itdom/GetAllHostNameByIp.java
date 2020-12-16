package com.itdom;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 3. 根据主机名获得IP 地址
 * static InetAddress getByName(String host）方法的作用： 在给定主机名的情况下确定主机
 * 的IP 地址。参数host 可以是计算机名、IP 地址， 也可以是域名。
 */
public class GetAllHostNameByIp {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress[] addressArray = InetAddress.getAllByName("CHEND-PC");
        InetAddress[] baiduAddresses = InetAddress.getAllByName("www.baidu.com");
        InetAddress[] ipStringAddressArray = InetAddress.getAllByName("192.168.10.54");
        for (int i = 0; i < addressArray.length; i++) {
            InetAddress myAddress = addressArray[i];
            System.out.println("myAddress.getHostAddress()="+myAddress.getHostAddress()+" "+myAddress.getClass().getName());
        }
        System.out.println();
        for (int i = 0; i < baiduAddresses.length; i++) {
            InetAddress baiduAddress = baiduAddresses[i];
            System.out.println("baiduAddress.getHostAddress()="+baiduAddress.getHostAddress()+" "+baiduAddress.getClass().getName());
        }
        System.out.println();
        for (int i = 0; i < ipStringAddressArray.length; i++) {
            InetAddress ipAddress = ipStringAddressArray[i];
            System.out.println("ipAddress.getHostAddress()="+ipAddress.getHostAddress()+" "+ipAddress.getClass().getName());
        }
    }
}