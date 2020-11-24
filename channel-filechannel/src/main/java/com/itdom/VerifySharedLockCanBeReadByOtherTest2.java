package com.itdom;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 验证共享锁别人能读{@code VerifySharedLockCanBeReadByOtherTest2}
 * 注意，如果操作锁定的区域，就会出现异常；如果操作未锁定的区域，则不出现异常。
 */
public class VerifySharedLockCanBeReadByOtherTest2 {
    public static void main(String[] args) throws IOException, InterruptedException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("./VerifySharedLockCanBeReadByItselfTest.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        fileChannel.read(byteBuffer);
        byteBuffer.rewind();
        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.print((char)byteBuffer.get());
        }

        /**
         * 先执行VerifySharedLockCanBeReadByOtherTest再执行VerifySharedLockCanBeReadByOtherTest2可以防线第一个程序已经将文
         * 件锁住，但是程序2还是能够将数据读取得到，说明共享锁是可以被别人读取的。
         * 执行结果:
         * abcdefg
         * Process finished with exit code 0
         */

    }
}
