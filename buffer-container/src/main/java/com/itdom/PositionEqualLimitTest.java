package com.itdom;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class PositionEqualLimitTest {
    public static void main(String[] args) {
        byte[] byteArray = {1, 2, 3, 4, 54, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        /*

            public ByteBuffer put(byte x) {

        hb[ix(nextPutIndex())] = x;
        return this;
    }



        final int nextPutIndex() {                          // package-private
        int p = position;
        if (p >= limit)
            throw new BufferOverflowException();
        position = p + 1;
        return p;
    }
         */
        byteBuffer.position(2);
        Buffer limit = byteBuffer.limit(2);
        byteBuffer.put(new Integer(1).byteValue());

    }


}
