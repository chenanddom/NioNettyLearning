package com.itdom;

import java.net.InetSocketAddress;

/**
 *2. getHost队lame （）和getHostString （）方法的区别
 * public final String getH o stName（）方法的作用是获取主机名。注意，如果地址是用字面
 * IP 地址创建的，则此方法可能触发名称服务反向查找， 也就是利用DN S 服务通过IP 找到
 * 域名。
 * public final String getHo stString （）方法的作用是返回主机名或地址的字符串形式， 如果
 * 它没有主机名，则返回IP 地址。这样做的好处是不尝试反向查找。
 */
public class TheDifferenceBetweenGetHostNameAndGetHostStringTest {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("192.168.10.54", 80);
        InetSocketAddress inetSocketAddress2 = new InetSocketAddress("192.168.10.54", 80);
        System.out.println(inetSocketAddress.getHostName());
        System.out.println(inetSocketAddress2.getHostString());

    }
}
