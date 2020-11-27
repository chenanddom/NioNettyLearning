package com.itdom;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 验证独占锁别人不能读和程序VerifyExclusiveLockCannotBeReadByOthersTest相关联
 */
public class VerifyExclusiveLockCannotBeReadByOthersTest2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./VerifyExclusiveLockCannotBeReadByOthersTest.txt", "rw");

        FileChannel channel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        channel.read(byteBuffer);

        byteBuffer.rewind();

        for (int i = 0; i < byteBuffer.limit(); i++) {
            System.out.println((char) byteBuffer.get());
        }

        /**
         * 在启动了程序VerifyExclusiveLockCannotBeReadByOthersTest之后启动程序VerifyExclusiveLockCannotBeReadByOthersTest2可以看到如下的结果。
         *
         * Exception in thread "main" java.io.IOException: 另一个程序已锁定文件的一部分，进程无法访问。
         * 	at sun.nio.ch.FileDispatcherImpl.read0(Native Method)
         * 	at sun.nio.ch.FileDispatcherImpl.read(FileDispatcherImpl.java:61)
         * 	at sun.nio.ch.IOUtil.readIntoNativeBuffer(IOUtil.java:223)
         * 	at sun.nio.ch.IOUtil.read(IOUtil.java:197)
         * 	at sun.nio.ch.FileChannelImpl.read(FileChannelImpl.java:159)
         * 	at com.itdom.VerifyExclusiveLockCannotBeReadByOthersTest2.main(VerifyExclusiveLockCannotBeReadByOthersTest2.java:19)
         */
    }
}
