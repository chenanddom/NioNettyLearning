package com.itdom;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 验证共享锁自己能读
 * 注意，如果操作锁定的区域，就会出现异常；如果操作未锁定的区域，则不出现异常。
 */
public class VerifySharedLockCanBeReadByItselfTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("./VerifySharedLockCanBeReadByItselfTest.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        fileChannel.lock(1,2,true);
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        fileChannel.read(byteBuffer);
        byteBuffer.rewind();
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.print((char)byteBuffer.get());
        }


    }
}
