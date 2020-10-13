package com.itdom;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * position 不能大于其limit 。
 */
public class PositionCannotBeGreaterThanLimitTest {

    public static void main(String[] args) {

        byte[] byteArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        Buffer limit = byteBuffer.limit(5);
        byteBuffer.position(6);
    }
}
