package com.itdom;

import java.nio.ByteBuffer;

/**
 * 通过ByteBuffer 向硬盘存取数据时是需要将数据暂存在NM 的
 * 中间缓冲区，如果有频繁操作数据的情况
 * 发生， 则在每次操作时都会将数据暂存在
 * 口币4 的中间缓冲区，再交给ByteBuffer
 * 处理， 这样做就大大降低软件对数据的
 * 吞吐量，提高内存占有率，造成软件运
 * 行效率降低，这就是非直接缓冲区保存
 * 数据的过程，所以非直接缓冲区的这个
 * 弊端就由直接缓冲区解决了。
 */
public class DirectBufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
        System.out.println(byteBuffer.isDirect());
    }

}
