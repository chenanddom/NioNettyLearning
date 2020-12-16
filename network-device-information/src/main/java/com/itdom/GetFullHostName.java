package com.itdom;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 7 . 获得全限主机名和主机名
 * getCanonicalHostN ame（） 方法的作用是取得主机完全限定域名， 而getHostName（） 方法
 * 是取得主机别名。
 */
public class GetFullHostName {
    public static void main(String[] args) throws UnknownHostException {
        //使用getLocalHost()创建InetAddress
        //getCanonicalHostName()和getHostName都是本地名称
        InetAddress address1 = InetAddress.getLocalHost();
        System.out.println("A1 "+address1.getCanonicalHostName());
        System.out.println("A2 "+address1.getHostName());
        System.out.println();
        //使用域名创建InetAddress
        InetAddress address2 = InetAddress.getByName("www.ibm.com");
        System.out.println("B1 "+address2.getCanonicalHostName());
        System.out.println("B2 "+address2.getHostName());
        System.out.println();
        InetAddress address3 = InetAddress.getByName("192.168.10.54");
        System.out.println("C1 "+address3.getCanonicalHostName());
        System.out.println("C2 "+address3.getHostName());
        System.out.println();

    }


}
