package com.itdom;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 * 释放直接缓冲区的内存
 * 使用allocateDirect（）方法创建的直接缓冲区如何释放内存呢？有两种办法， 一种是手动
 * 释放空间，另一种就是交给NM 进行处理。先来看第一种： 手动释放空间
 */
public class ReleaseDirectBufferMemoryTest {

    public static void main(String[] args) throws InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//1.手动释放内存
/*        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        byte[] byteArray = {1};
        System.out.println(Integer.MAX_VALUE);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            byteBuffer.put(byteArray);
        }
        System.out.println("put end!");
        Thread.sleep(10000);
        //程序允许10秒钟之后立即回收内存，也是回收"直接缓冲区的内存".
        Method clearMethod = byteBuffer.getClass().getMethod("clear");
        clearMethod.setAccessible(true);
        Object returnValue = clearMethod.invoke(byteBuffer);
        clearMethod.setAccessible(true);
        clearMethod.invoke(returnValue);*/
//2.JVM自动释放缓冲区内存
        /*
        ／／此程序多次运行后， 一直在耗费内存，
        ／／进程结束后，也不会马土回收内存，
        ／／而是会在某个时机触发GC 垃圾回收器进行内存的回收
         */
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        byte[] byteArray = {1};
        System.out.println(Integer.MAX_VALUE);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            byteBuffer.put(byteArray);
        }
        System.out.println("put end!");
    }


}
