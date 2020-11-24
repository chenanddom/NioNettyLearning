package com.itdom;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 验证共享锁别人能读{@code VerifySharedLockCanBeReadByOtherTest2}
 * 注意，如果操作锁定的区域，就会出现异常；如果操作未锁定的区域，则不出现异常。
 */
public class VerifySharedLockCanBeReadByOtherTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("./VerifySharedLockCanBeReadByItselfTest.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        fileChannel.lock(1,2,true);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
