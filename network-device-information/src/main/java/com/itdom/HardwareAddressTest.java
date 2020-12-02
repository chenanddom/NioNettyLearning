package com.itdom;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * public byte[] getHardwareAddress（）方法的作用：获得网卡的硬件地址。什么是硬件地
 * 址？ 硬件地址也称为物理地址，或MAC ( Media Access Control ，媒体访问控制） 。它用来定
 * 义网络设备的位置， 也是网卡设备的唯一ID 采用十六进制表示一共48 位。MAC 地址
 * 包含由IEEE 的注册管理机构RA 负责给不同厂商分配的唯一标识，因此，正规厂商出厂的
 * 网卡的MAC 地址永远不会出现重复。
 * 物理地址、MAC 地址和硬件地址， 这三者的含义是一样的。
 */
public class HardwareAddressTest {

    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()){
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println(" getName获取网络设备的名称="+networkInterface.getName());
            System.out.println(" getDisplayName获取网络设备的名称="+networkInterface.getDisplayName());
            System.out.println(" getHardwareAddress获取网卡的物理地址=");
            byte[] address = networkInterface.getHardwareAddress();
            if (address!=null && address.length>0) {
                for (int i = 0; i < address.length - 1; i++) {
                    System.out.print(address[i] + " ");
                }
            }
        }
    }
}
