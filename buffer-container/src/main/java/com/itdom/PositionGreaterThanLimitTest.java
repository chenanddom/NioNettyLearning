package com.itdom;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 如果position 大于新的limit ，则position 的值就是新limit 的值。
 */
public class PositionGreaterThanLimitTest {
    public static void main(String[] args) {

        byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        System.out.println("position0="+byteBuffer.position());
        byteBuffer.position(6);
        System.out.println("position1="+byteBuffer.position());
        /*
            public final Buffer limit(int newLimit) {
        if ((newLimit > capacity) || (newLimit < 0))
            throw new IllegalArgumentException();
        limit = newLimit;
        if (position > newLimit) position = newLimit;
        if (mark > newLimit) mark = -1;
        return this;
    }
         */
        byteBuffer.limit(4);
        System.out.println("position1="+byteBuffer.position());
    }
}
