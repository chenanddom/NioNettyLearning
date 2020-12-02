package com.itdom;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 3. 根据主机名获得IP 地址
 * static InetAddress getByName(String host）方法的作用： 在给定主机名的情况下确定主机
 * 的IP 地址。参数host 可以是计算机名、IP 地址， 也可以是域名。
 */
public class GetHostNameByIp {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress myAddress = InetAddress.getByName("CHEND-PC");
        InetAddress baiduAddress = InetAddress.getByName("www.baidu.com");
        InetAddress ipAddress = InetAddress.getByName("192.168.10.54");
        InetAddress localhostAddress = InetAddress.getByName("localhost");
        System.out.println(localhostAddress.getClass().getName()+" "+localhostAddress.getHostAddress());
        System.out.println(myAddress.getClass().getName()+" "+myAddress.getHostAddress());
        System.out.println(baiduAddress.getClass().getName()+" "+baiduAddress.getHostAddress());
        System.out.println(ipAddress.getClass().getName()+" "+ipAddress.getHostAddress());
        //不存在的ip会出现异常
        InetAddress notIpAddress = InetAddress.getByName("192.168.0.890");
        System.out.println(notIpAddress.getClass().getName()+" "+notIpAddress.getHostAddress());
        InetAddress notDomainAddress = InetAddress.getByName("www.baidu123654.com");
        System.out.println(notDomainAddress.getClass().getName()+" "+notDomainAddress.getHostAddress());
    }
}