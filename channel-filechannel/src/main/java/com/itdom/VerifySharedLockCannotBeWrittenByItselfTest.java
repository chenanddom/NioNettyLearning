package com.itdom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 验证共享锁自己不能写（出现异常）
 * 注意，如果操作锁定的区域，就会出现异常；如果操作未锁定的区域，则不出现异常。
 */
public class VerifySharedLockCannotBeWrittenByItselfTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile("./VerifySharedLockCannotBeWrittenByItselfTest.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        fileChannel.lock(1,2,true);
        fileChannel.write(ByteBuffer.wrap("123456".getBytes()));
        /**
         * 运行出现如下的结果:
         * xception in thread "main" java.io.IOException: 另一个程序已锁定文件的一部分，进程无法访问。
         * 	at sun.nio.ch.FileDispatcherImpl.write0(Native Method)
         * 	at sun.nio.ch.FileDispatcherImpl.write(FileDispatcherImpl.java:75)
         * 	at sun.nio.ch.IOUtil.writeFromNativeBuffer(IOUtil.java:93)
         * 	at sun.nio.ch.IOUtil.write(IOUtil.java:65)
         * 	at sun.nio.ch.FileChannelImpl.write(FileChannelImpl.java:211)
         * 	at com.itdom.VerifySharedLockCannotBeWrittenByItselfTest.main(VerifySharedLockCannotBeWrittenByItselfTest.java:19)
         * 	异常信息有中文的内容存在，是因为使用了中文版的操作系统，在操作系统层面进行
         * 了异常的处理，再把这个中文异常信息传给NM ， 然后在控制台进行显示。上述结果说明
         * FileChannel 通道对文件进行操作时＼还需要调用操作系统的API 进行实现，这点已经在异
         * 常信息中的writeO(Native Method）得到了验证。
         */


    }
}
