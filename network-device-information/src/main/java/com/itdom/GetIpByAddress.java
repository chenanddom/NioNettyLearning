package com.itdom;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *5. 根据IP 地址byte[]addr 获得lnetAddress 对象
 * static InetAddress getByAddress(byte[] addr）方法的作用： 在给定原始IP 地址的情况下，
 * 返回InetAddress 对象。参数按网络宇节顺序：地址的高位字节位于getAddress()[O ］中。
 */
public class GetIpByAddress {
    public static void main(String[] args) throws UnknownHostException {

        byte[] byteArray = new byte[]{-64,-88,10,54};
        InetAddress myAddress = InetAddress.getByAddress(byteArray);
        System.out.println("myAddress.getHostAddresss()="+myAddress.getHostAddress());
        System.out.println("myAddress.getHostName()="+myAddress.getHostName());
        System.out.println("myAddress.getClass().getHostName()="+myAddress.getClass().getName());
    }
}
