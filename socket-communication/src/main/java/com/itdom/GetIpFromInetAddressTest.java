package com.itdom;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 *public final InetAddress getAddress（）方法的作用是获取InetAddress 对象。
 */
public class GetIpFromInetAddressTest {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8088);
        InetAddress address = inetSocketAddress.getAddress();
        byte[] addressAddress = address.getAddress();
        for (int i = 0; i < addressAddress.length; i++) {
            System.out.print((byte)addressAddress[i]+" ");
        }
    }
}
