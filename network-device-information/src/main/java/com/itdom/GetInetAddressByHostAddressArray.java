package com.itdom;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 6. 根据主机名和IP 地址byte[]addr 获得lnetAddress 对象
 * static InetAddress getByAddress(String host, byte[] addr）方法的作用：根据提供的主机名
 * 和IP 地址创建InetAddress ，并不对host 的有效性进行验证。
 * 其中参数host 仅仅是参数addr 的一个说明及备注，代表addr 这个地址所属的主机名是host。
 */
public class GetInetAddressByHostAddressArray {
    public static void main(String[] args) throws UnknownHostException {
        byte[] byteArray = new byte[]{-64,-88,10,54};
        InetAddress myAddress = InetAddress.getByAddress("cccccdddddddd", byteArray);
        System.out.println("myAddress.getHostAddress()="+myAddress.getHostAddress());
        System.out.println("myAddress.getHostName()="+myAddress.getHostName());
        System.out.println("myAddress.getClass().getName="+myAddress.getClass().getName());
    }




}
