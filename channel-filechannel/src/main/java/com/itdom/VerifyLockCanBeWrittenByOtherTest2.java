package com.itdom;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 验证独占锁别人不能写（出现异常）
 */
public class VerifyLockCanBeWrittenByOtherTest2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("./VerifyLockCanBeWrittenByOtherTest", "rw");
        FileChannel accessFileChannel = randomAccessFile.getChannel();
        accessFileChannel.write(ByteBuffer.wrap("123456".getBytes()));
        /**
         * 限制现执VerifyLockCanBeWrittenByOtherTest程序再执行VerifyLockCanBeWrittenByOtherTest2，行结果：
         * Exception in thread "main" java.io.IOException: 另一个程序已锁定文件的一部分，进程无法访问。
         * 	at sun.nio.ch.FileDispatcherImpl.write0(Native Method)
         * 	at sun.nio.ch.FileDispatcherImpl.write(FileDispatcherImpl.java:75)
         * 	at sun.nio.ch.IOUtil.writeFromNativeBuffer(IOUtil.java:93)
         * 	at sun.nio.ch.IOUtil.write(IOUtil.java:65)
         * 	at sun.nio.ch.FileChannelImpl.write(FileChannelImpl.java:211)
         * 	at com.itdom.VerifyLockCanBeWrittenByOtherTest2.main(VerifyLockCanBeWrittenByOtherTest2.java:16)
         */
    }
}
