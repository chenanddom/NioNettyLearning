package com.itdom;

import org.omg.CORBA.TRANSACTION_MODE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 文件锁定要么是独占的，妥么是共享的。共享锁定可阻止其他并发运行的程序获取重
 * 叠的独占锁定，但是九许该程序获取重叠的共享锁定。1虫占锁定则阻止其他程序获取任一类
 * 型的重叠锁定。
 * 共享锁之间、独占锁之间，以及共享锁与独占锁之间的关系，有以下4 种情况e :
 * 1 ）共享锁与共享锁之间是非互斥关系；
 * 2 ） 共享锁与独占锁之间是互斥关系；
 * 3 ） 独占锁与共享锁之间是互斥关系；
 * 4 ） 独占锁与独占锁之间是互斥关系。
 * 首先测试：共享锁与共享锁之间是非互斥关系。
 *
 * 验证共享锁与共享锁之间是非亘斥关系关联程序VeriftSharedLockAndSharedLockNonExclusiveTest
 */
public class VeriftSharedLockAndSharedLockNonExclusiveTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./VeriftSharedLockAndSharedLockNonExclusiveTest.txt", "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        accessFileChannel.lock(0,Long.MAX_VALUE, true);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
