package com.itdom;

import java.nio.ByteBuffer;

/**
 * compareTo(ByteBuffer that）方法的作用： 将此缓冲区与另一个缓冲区进行比较。比较两个字节缓冲区的方法是按字典顺序
 * 比较它们的剩余元素序列，而不考虑每个序列在其对应缓冲区中的起始位置.
 */
public class CompareToMethodTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{1, 2, 3, 4, 5, 6});
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
        byteBuffer.position(0);
        byteBuffer1.position(0);
        /*
         public int compareTo(ByteBuffer that) {
        int n = this.position() + Math.min(this.remaining(), that.remaining());
        for (int i = this.position(), j = that.position(); i < n; i++, j++) {
            int cmp = compare(this.get(i), that.get(j));
            if (cmp != 0)
                return cmp;
        }
        return this.remaining() - that.remaining();
    }
         */
        System.out.println("A=" + byteBuffer1.compareTo(byteBuffer));
    }
}
