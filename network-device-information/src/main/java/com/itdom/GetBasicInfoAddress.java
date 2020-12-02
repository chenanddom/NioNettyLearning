package com.itdom;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 2. 获得本地主机和回环地址的基本信患
 * static InetAddress getLocalHost（）方法的作用：返回本地主机的IP 地址信息。如果本
 * 机拥有多个IP ，则getLocalHost（）方法只返回下标为［ O ］的第一个IP 。如果想返回本机
 * 全部的IP ，就需要使用getAl!ByName （）方法。在JDK 源代码中， getLocalHost（）方法与
 * getA!lByName（）方法调用相同的方法： private static lnetAddress[] getAddressesFromNameSe
 * rvice(String host, InetAddress reqAddr），来实现取得InetAddress ［］数组。
 */
public class GetBasicInfoAddress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.print(" localhost.getAddress()地址为=");
        byte[] addressArray = localHost.getAddress();
        for (int i = 0; i < addressArray.length; i++) {
            System.out.print(" "+addressArray[i]+" ");
        }
        System.out.println();
        System.out.println(" "+localHost.getClass().getName());
        System.out.println();
        System.out.print(" inetAddress.getLoopbackAddress()地址为=");
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
        byte[] loopbackAddr = loopbackAddress.getAddress();
        for (int i = 0; i < loopbackAddr.length; i++) {
            System.out.print(" "+loopbackAddr[i]+" ");
        }
        System.out.println();
        System.out.println(" "+loopbackAddress.getClass().getName());
    }
}
