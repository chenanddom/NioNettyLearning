package com.itdom;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 如果定义了mark ，则在将pos ition 或l imit 调整为小于该mark 的值时，该mark 被
 * 丢弃。
 */
public class DiscardMarkGreaterThanPositionAndLimitTest {

    public static void main(String[] args) {

        byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);

        byteBuffer.position(5);
        byteBuffer.mark();
        System.out.println("bytebuffer.position()="+byteBuffer.position());
        /**
         *     public final Buffer position(int newPosition) {
         *         if ((newPosition > limit) || (newPosition < 0))
         *             throw new IllegalArgumentException();
         *         position = newPosition;
         *         if (mark > position) mark = -1;
         *         return this;
         *     }
         *
         *         public final Buffer reset() {
         *         int m = mark;
         *         if (m < 0)
         *             throw new InvalidMarkException();
         *         position = m;
         *         return this;
         *     }
         */

        byteBuffer.position(3);
        byteBuffer.reset();
        System.out.println("bytebuffer.position()="+byteBuffer.position());

    }
}
