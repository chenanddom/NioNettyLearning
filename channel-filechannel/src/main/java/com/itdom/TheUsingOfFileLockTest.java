package com.itdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Filelock 类的使用
 * FileLock 类表示文件区域锁定的标记。每次通过FileChannel 类的lock（）或tryLock（）方
 * 法获取文件上的锁定时，就会创建一个FileLock （文件锁定） 对象。
 * 文件锁定对象最初是有效的。通过调用release（）方法、关闭用于获取该锁定的通道，
 * 或者终止Java 虚拟机（以先到者为准）来释放锁定之前，该对象一直是有效的。可通过调用
 * 锁定的isValid（）方法来测试锁定的有效性。
 * 文件锁定要么是独占的，要么是共享的。共享锁定可阻止其他并发运行的程序获取重
 * 叠的独占锁定，但是允许该程序获取重叠的共享锁定。独占锁定则阻止其他程序获取任一类
 * 型的重叠锁定。一旦释放某个锁定后，它就不会再对其他程序所获取的锁定产生任何影响。
 * 可通过调用某个锁定的isShared（）方法来确定它是独占的还是共享的。某些平台不支持
 * 共享锁定，在这种情况下，对共享锁定的请求被自动转换为对独占锁定的请求。
 * 单个Jav a 虚拟机在某个特定文件上所保持的锁定是不重叠的。要测试某个候选锁定范
 * 围是否与现有锁定重叠，可使用overlaps （）方法。
 * 文件锁定对象记录了在其文件上保持锁定的文件通道、该锁定的类型和有效性，以及
 * 锁定区域的位置和大小。只有锁定的有效性是随时间而更改的；锁定状态的所有其他方面都
 * 是不可变的。
 * 文件锁定以整个Java 虚拟机来保持。但它们不适用于控制同一虚拟机内多个线程对文
 * 件的访问。
 * 多个并发线程可安全地使用文件锁定对象。
 * FileLock 类具有平台依赖性，此文件锁定API 直接映射到底层操作系统的本机锁定机
 * 制。因此， 无论程序是用何种语言编写的，某个文件上所保持的锁定对于所有访问该文件的
 * 程序来说都应该是可见的。
 * 由于某个锁定是否实际阻止另一个程序访问该锁定区域的内容是与系统相关的，因此
 * 是未指定的。有些系统的本机文件锁定机制只是劝告的，意味着为了保证数据的完整性，各
 * 个程序必须遵守己知的锁定协议。其他系统本机文件锁定是强制的，意味着如果某个程序锁
 * 定了某个文件区域， 则实际上阻止其他程序以违反该锁定的方式访问该区域。但在其他系统
 * 上，本机文件锁定是劝告的还是强制的可以以每个文件为基础进行配置。为确保平台间的一
 * 致性和正确性，强烈建议将此API 提供的锁定作为劝告锁定来使用。
 * 在有些系统上， 在某个文件区域上获取强制锁定会阻止该区域被j ava .nio. channe l s .
 * FileChannel#map 映射到内存，反之亦然。组合锁定和映射的程序应该为此组合的失败做好
 * 准备。
 * 在有些系统上，关闭某个通道会释放Java 虚拟机在底层文件上所保持的所有锁定，而
 * 不管该锁定是通过该通道获取的，还是通过同一文件上打开的另一个通道获取的。强烈建议
 * 在某个程序内使用唯一的通道来获取任意给定文件上的所有锁定。
 */
public class TheUsingOfFileLockTest {
    public static void main(String[] args) throws IOException {
        File file = new File("./TheUsingOfFileLockTest.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        System.out.println("fileChannel.hashCode()=" + accessFileChannel.hashCode());
        //共享锁定
        FileLock lock = accessFileChannel.lock(1, 10, true);
        System.out.println("A position=" + lock.position() + " size()=" + lock.size()
                + " isValid()=" + lock.isValid() + " isShared()=" + lock.isShared()
                + " channel().hashCode()=" + accessFileChannel.hashCode());
        lock.release();
        //独占锁定

        lock = accessFileChannel.lock(1, 10, false);
        System.out.println("B position=" + lock.position() + " size()=" + lock.size()
                + " isValid()=" + lock.isValid() + " isShared()=" + lock.isShared()
                + " channel().hashCode()=" + accessFileChannel.hashCode());
        /**
         * FileLock 类的close（）方法在源代码内部调用的是release（）方法，源代码如下：
         *     public final void close() throws IOException {
         * release();
         * }
         */
        lock.close();
        accessFileChannel.close();
        System.out.println("C position=" + lock.position() + " size()=" + lock.size()
                + " isValid()=" + lock.isValid() + " isShared()=" + lock.isShared()
                + " channel().hashCode()=" + accessFileChannel.hashCode());

    }
}
