package com.itdom;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * AsynchronousFileChannel 类用于读取、写入和操作文件的异步通道。
 * 在通过调用此类定义的open （）方法打开文件时，将创建一个异步文件通道。该文件包
 * 含可读写的、可查询其当前大小的可变长度的字节序列。当写人字节超出其当前大小时，文
 * 件的大小会增加。文件的大小在截断时会减小。
 * 异步文件通道在文件中没有当前位置，而是将文件位置指定给启动异步操作的每个读
 * 取和写入方法。CompletionHandler 被指定为参数，并被调用以消耗1/ 0 操作的结果。此类
 * 还定义了启动异步操作的读取和写入方法，并返回F u阳re 对象以表示操作的挂起结果。将
 * 来可用于检查操作是否已完成，等待完成，然后检索结果。
 * 除了读写操作之外，此类还定义了以下操作：
 * 1 ）对文件所做的更新可能会被强制到底层存储设备，以确保在发生系统崩溃时不会丢
 * 失数据。
 * 2 ）文件的某个区域可能被其他程序的访问锁定。
 * AsynchronousFileChanne l 与一个线程池关联，任务被提交来处理1/0 事件，并发送到使
 * 用通道上I/O 操作结果的CompletionHandler 对象。在通道上启动的I/O 操作的CompletionHandler
 * 保证由线程池中的一个线程调用（这样可以确保CompletionHand ler 程序由具有预
 * 期标识的线程运行） 。如果I/O 操作立即完成，并且起始线程本身是线程池中的线程， 则
 * 启动线程可以直接调用完成处理程序。当创建AsynchronousFileChannel 而不指定线程池
 * 时，该通道将与系统相关的默认线程池关联，该线程池可能与其他通道共享。默认线程池由
 * AsynchronousChannelGroup 类定义的系统属性配置。
 * 此类型的通道可以安全地由多个并发线程使用。可以在任何时候调用close （）方法，
 * 如通道接口所指定的那样。这将导致通道上的所有未完成的异步操作都使用异常AsynchronousCloseException
 * 。多个读写操作在同一时间可能是未完成的。当多个读写操作未完
 * 成时，将不指定1/0 操作的顺序以及调用Comp letionHandler 程序的顺序。特别是，它们
 * 没有保证按照行动的启动顺序执行。读取或写人时使用的ByteBuffers 不安全，无法由多
 * 个并发I/O 操作使用。此外， 在启动I/O 操作之后，应注意确保在操作完成后才能访问缓
 * 冲区。
 * 与日leChannel 一样，此类的实例提供的文件的视图保证与同一程序中其他实例提供的
 * 同一文件的其他视图一致。但是，该类的实例提供的视图可能与其他并发运行的程序所看到
 * 的视图一致，也可能不一致，这是由于底层操作系统所执行的缓存和网络文件系统协议引起
 * 的延迟。无论编写这些程序的语言是什么，也无论它们是在同一台机器上运行还是在其他机
 * 器上，都是如此。任何此类不一致的确切性质都依赖于系统，因此未指定。
 *
 * 获取此通道文件的独占锁
 *
 * public final Future<FileLock> lock（）方法的作用是获取此通道文件的独占锁。此方法启
 * 动一个操作以获取此通道的文件的独占锁。该方法返回一个表示操作的挂起结果的Future
 * 对象。Future 的get（） 方法在成功完成时返回FileLock 。调用此方法的行为及调用的方式
 * 与代码ch.lock(OL, Long.MAX_ VALUE, false）完全相同。返回值表示待定结果的Future
 * 对象。
 */
public class GetExclusiveLockForTheChannelTest {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Path path = Paths.get("./asynchrous-io/files/a.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        Future<FileLock> future = channel.lock();
        FileLock fileLock = future.get();
        System.out.println("A get lock time="+System.currentTimeMillis());
        Thread.sleep(8000);
        fileLock.release();
        System.out.println("A release lock time="+System.currentTimeMillis());
        channel.close();
        /**先执行GetExclusiveLockForTheChannelTest再执行GetExclusiveLockForTheChannelTest2
         * 执行结果：
         *A get lock time=1609898557676
         * A release lock time=1609898565676
         */
    }
}
