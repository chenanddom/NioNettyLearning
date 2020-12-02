package com.itdom;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Demo {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()){
            NetworkInterface element = interfaces.nextElement();
            System.out.println(" getName获取网络设备名称="+element.getName());
            System.out.println(" getDisplayName获取网络设备显示名称="+element.getDisplayName());
            System.out.println(" getaindex获取网络接口的索引="+element.getIndex());
            System.out.println(" isUp是否开启并运行="+element.isUp());
            System.out.println(" isLoopback是否为回调接口="+element.isLoopback());
            //网络接口的最大传输单元
            System.out.println(" getMTU最大传输单元"+element.getMTU());
            System.out.println();
        }
    }
}
