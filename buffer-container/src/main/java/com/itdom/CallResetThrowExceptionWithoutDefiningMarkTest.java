package com.itdom;

import java.nio.ByteBuffer;

public class CallResetThrowExceptionWithoutDefiningMarkTest {
    public static void main(String[] args) {
        byte[] byteArray = {1, 2, 3, 4, 5, 5, 6, 7, 8, 9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        //没有定义mark标记就执行会抛InvalidMarkException
        byteBuffer.reset();
    }
}
