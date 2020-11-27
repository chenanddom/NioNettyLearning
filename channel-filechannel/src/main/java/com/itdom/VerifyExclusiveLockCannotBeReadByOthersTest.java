package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 *验证独占锁别人不能读和程序VerifyExclusiveLockCannotBeReadByOthersTest2相关联
 */
public class VerifyExclusiveLockCannotBeReadByOthersTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./VerifyExclusiveLockCannotBeReadByOthersTest.txt", "rw");

        FileChannel channel = randomAccessFile.getChannel();

        channel.lock(1,2,false);

        Thread.sleep(Integer.MAX_VALUE);


    }
}
