package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 验证独占锁别人不能写（出现异常）
 */
public class VerifyLockCanBeWrittenByOtherTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./VerifyLockCanBeWrittenByOtherTest", "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        accessFileChannel.lock(1,2,false);
        Thread.sleep(Integer.MAX_VALUE);

    }
}
