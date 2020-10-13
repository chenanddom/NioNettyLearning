package com.itdom;

import java.nio.ByteBuffer;

public class CapacityCannotBeNegativeTest {
    public static void main(String[] args) {
        try {
        ByteBuffer byteBuffer = ByteBuffer.allocate(-1);
        }catch (IllegalArgumentException e){
            System.out.println("capacity不能设置为负数");
        }
    }
}
