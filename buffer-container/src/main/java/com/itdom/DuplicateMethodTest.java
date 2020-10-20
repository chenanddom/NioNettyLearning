package com.itdom;

import java.nio.ByteBuffer;

/**
 * ByteBuffer duplicate （）方法的作用：创建共享此缓冲区内容的新的字节缓冲区。新缓
 * 冲区的内容将为此缓冲区的内容。此缓冲区内容的更改在新缓冲区中是可见的，反之亦然。
 * 在创建新的缓冲区时，容量、限制、位置和标记的值将与此缓冲区相同，但是这两个缓冲区
 * 的位置、界限和标记值是相互独立的。当且仅当此缓冲区为直接缓冲区时，新缓冲区才是直
 * 接缓冲区。当且仅当此缓冲区为只读时，新缓冲区才是只读的。
 */
public class DuplicateMethodTest {
    public static void main(String[] args) {
        byte[] byteArray = {1,2,3,4,5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer.position(2);
        System.out.println("bytebuffer.capacity()="+byteBuffer.capacity()+" bytebuffer.position()="+byteBuffer.position()+" bytebuffer.limit()="+byteBuffer.limit());
        /**
         * 这个复制会从当前的位置开始
         */
        ByteBuffer byteBuffer1 = byteBuffer.slice();
        /**
         * 这个操作会把所以的数据都复制给第二个缓冲区
         */
        ByteBuffer byteBuffer2 = byteBuffer.duplicate();
        /*
            bytebuffer3和bytebuffer指向的是同一个地址
         */
        ByteBuffer byteBuffer3 = byteBuffer;

        System.out.println("bytebuffer1.capacity()="+byteBuffer1.capacity()+" bytebuffer1.position()="+byteBuffer1.position()+" bytebuffer1.limit()="+byteBuffer1.limit());
        System.out.println("bytebuffer2.capacity()="+byteBuffer2.capacity()+" bytebuffer2.position()="+byteBuffer2.position()+" bytebuffer2.limit()="+byteBuffer2.limit());

        byteBuffer1.position(0);

        for (int i = byteBuffer1.position(); i <byteBuffer1.limit()  ; i++) {
            System.out.print(byteBuffer1.get(i)+" ");
        }

        System.out.println();
        byteBuffer2.position(0);
        for (int i = byteBuffer2.position(); i <byteBuffer2.limit()  ; i++) {
            System.out.print(byteBuffer2.get(i)+" ");
        }
        System.out.println();
        System.out.println("----------方法创建新的缓冲区后，在新缓冲区中添加数据时，被复制的缓冲区中的值也发生改变，说明这两个缓冲区用的是同一个byte[]---------------");
        //被复制的缓冲区也会反应出该变化
        byteBuffer2.put(1,(byte) 22);
        byteBuffer.position(0);
        for (int i = byteBuffer.position(); i <byteBuffer.limit()  ; i++) {
            System.out.print(byteBuffer.get(i)+" ");
        }
    }
}
