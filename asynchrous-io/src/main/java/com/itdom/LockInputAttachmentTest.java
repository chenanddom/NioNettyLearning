package com.itdom;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 执行锁定与传入附件及整合接口Completion Handler
 * 如果public final <A> void lock(A attachment,CompletionHandler<FileLock,? super A> handler)
 * 方法获得不到锁，则一直等待。
 */
public class LockInputAttachmentTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".asynchrous-io/files/a.txt");
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
        System.out.println("A begin");
    }

}
