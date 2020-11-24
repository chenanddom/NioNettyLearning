package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *验证独占锁自己能写
 */
public class VerifyLockCanBeWrittenByItselfTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./VerifyLockCanBeWrittenByItselfTest.txt", "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        accessFileChannel.lock(1,2,false);
        accessFileChannel.write(ByteBuffer.wrap("123456".getBytes()));
        accessFileChannel.close();
        randomAccessFile.close();

    }
}
