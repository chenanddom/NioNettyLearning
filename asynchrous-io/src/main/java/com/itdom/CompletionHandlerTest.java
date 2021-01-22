package com.itdom;

import java.io.IOException;
import java.nio.channels.AsynchronousChannel;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * public final <A > void lock(A attachment,C omp letionHandler< FileLock,? super A > h a ndler)
 * 方法的作用是获取此通道文件的独占锁。此方法启动一个操作以获取此通道文件的给定区
 * 域的锁。handler 参数是在获取锁（ 或操作失败）时调用的Comp letionHandler 对象。传递给
 * CompletionHandl er 的结果是生成的FileLock 。
 * <p>
 * 调用此方法ch.lock(att, handler)的行为及方式与ch.lock(OL , Long.MAX_ VALUE, false,
 * att， handler）完全相同。参数A 代表附件的数据类型。参数attachment 代表要附加到IO 操性
 * 的对象，可以为空。Comp l etionHand l er 代表处理程序，用于消耗结果的处理程序
 */
public class CompletionHandlerTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("./asynchrous-io/files/a.txt");
        final AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        System.out.println("begin time=" + System.currentTimeMillis());
        channel.close();
        channel.lock("我是附加值", new CompletionHandler<FileLock, String>() {
            @Override
            public void completed(FileLock result, String attachment) {
                try {
                    System.out.println(" public void completed(FileLock result, String attachment) attachment=" + attachment);
                    result.release();
//                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            /*
            public void failed(Throwable exc, A attachment）方法被调用的时机是出现I/O 操作异常时。
             */
            @Override
            public void failed(Throwable exc, String attachment) {
                System.out.println("public void failed(Throwable exc, String attachment) attachment="+attachment);
                System.out.println("getMessage="+exc.getMessage());
                System.out.println("exec.getClass().getName()="+exc.getClass().getName());
            }
        });
        System.out.println(" end time="+System.currentTimeMillis());
        Thread.sleep(3000);
    }
}
