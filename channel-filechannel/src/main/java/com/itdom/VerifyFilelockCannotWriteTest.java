package com.itdom;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 注意，如果操作锁定的区域，就会出现异常；如果操作未锁定的区域，则不出现异常
 */
public class VerifyFilelockCannotWriteTest {
    public static void main(String[] args) throws IOException {
        //验证共享锁自己不能写入(会出现异常)
        RandomAccessFile fileOutputStream = new RandomAccessFile("./VerifyFilelockCannotWriteTest.txt","rw");
        FileChannel channel = fileOutputStream.getChannel();
        channel.lock(1,2,true);
        channel.write(ByteBuffer.wrap("1234456575".getBytes()));
        /**
         * 程序的运行的结果
         * Exception in thread "main" java.io.IOException: 另一个程序已锁定文件的一部分，进程无法访问。
         * 	at sun.nio.ch.FileDispatcherImpl.write0(Native Method)
         * 	at sun.nio.ch.FileDispatcherImpl.write(FileDispatcherImpl.java:75)
         * 	at sun.nio.ch.IOUtil.writeFromNativeBuffer(IOUtil.java:93)
         * 	at sun.nio.ch.IOUtil.write(IOUtil.java:65)
         * 	at sun.nio.ch.FileChannelImpl.write(FileChannelImpl.java:211)
         * 	at com.itdom.VerifyFilelockCannotWriteTest.main(VerifyFilelockCannotWriteTest.java:19)
         */

    }
}
