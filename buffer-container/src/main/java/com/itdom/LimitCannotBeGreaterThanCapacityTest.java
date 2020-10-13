package com.itdom;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * limit 不能大于其capacity 。
 */
public class LimitCannotBeGreaterThanCapacityTest {

    public static void main(String[] args) {

        byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        /**
         *     public final Buffer limit(int newLimit) {
         *         if ((newLimit > capacity) || (newLimit < 0))
         *             throw new IllegalArgumentException();
         *         limit = newLimit;
         *         if (position > newLimit) position = newLimit;
         *         if (mark > newLimit) mark = -1;
         *         return this;
         *     }
         */
        Buffer limit = byteBuffer.limit(10);
    }
}
