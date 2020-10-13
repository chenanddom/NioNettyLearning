package com.itdom;

import java.nio.ByteBuffer;

/**
 * fi n a l Buffer clear（）方法的作用： 还原缓冲区到初始的状态， 包含将位置设置为0 ，将限
 * 制设置为容量，并丢弃标记， 即“一切为默认” 。
 *
 * 需要注意的是， clear（）方法“不能真正清除” 缓冲区中的数据，虽然从名称来看它似乎
 * 能够这样做，这样命名是因为它在多数情况下确实有清除数据的作用，那么怎么“清除”数
 * 据呢？ 例如，调用代码“ buf. clear（）；”后将缓冲区的状态进行还原，包含将position （位置）
 * 归0 ，再执行写人新数据的代码，将最新版的数据由索引位置。开始覆盖，这样就将缓冲区
 * 中的旧值用新值覆盖了，相当于数据被清除了。
 */
public class ClearBufferTest {
    public static void main(String[] args) {
        byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        byteBuffer.position(7);
        System.out.println("byteBuffer.position()="+byteBuffer.position());
        /**
         *     public final Buffer clear() {
         *         position = 0;
         *         limit = capacity;
         *         mark = -1;
         *         return this;
         *     }
         */
        byteBuffer.clear();
        System.out.println("byteBuffer.position()="+byteBuffer.position());

    }
}
