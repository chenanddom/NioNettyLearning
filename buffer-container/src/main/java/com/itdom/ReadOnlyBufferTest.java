package com.itdom;

import java.nio.ByteBuffer;

/**
 * asReadOnlyBuffer（）方法的作用：创建共享此缓冲区内容的新的只读字节缓冲区。新
 * 缓冲区的内容将为此缓冲区的内容。此缓冲区内容的更改在新缓冲区中是可见的，但新缓冲
 * 区将是只读的并且不允许修改共享内容。两个缓冲区的位置、限制和标记值是相互独立的。
 * 新缓冲区的容量、限制、位置和标记值将与此缓冲区相同。
 */
public class ReadOnlyBufferTest {
    public static void main(String[] args) {
        byte[] byteArrayIn = {1,2,3,4,5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArrayIn);
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println("bytebuffer1.isReadOnly()="+byteBuffer.isReadOnly());
        System.out.println("readOnlyBuffer.isReadOnly()="+readOnlyBuffer.isReadOnly());
        readOnlyBuffer.rewind();
        readOnlyBuffer.put((byte)1234);
    }

}

