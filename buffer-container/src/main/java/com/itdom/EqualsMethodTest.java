package com.itdom;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * 比较缓冲区的内容是否相同有两种方法： equals （）和compareTo （）
 */
public class EqualsMethodTest {

    public static void main(String[] args) {
        byte[] byteArray = {1, 2, 3, 4, 5};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
        /*
         public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (!(ob instanceof ByteBuffer))
            return false;
        ByteBuffer that = (ByteBuffer)ob;
        if (this.remaining() != that.remaining())
            return false;
        int p = this.position();
        for (int i = this.limit() - 1, j = that.limit() - 1; i >= p; i--, j--)
            if (!equals(this.get(i), that.get(j)))
                return false;
        return true;
    }

    从equals （）方法的源代码中可以分析出运算的4 个主要逻辑。
1 ）判断是不是自身，如果是自身， 则返回为true 。
2 ）判断是不是ByteBuffer 类的实例，如果不是， 则返回fal se 。
3 ）判断remaining（） 值是否一样，如果不一样， 则返回false 。
4 ）判断两个缓冲区中的position 与limit 之间的数据是否完全一样，只要有一个字节不
同，就返回false ， 否则返回true 。
通过源代码来看，两个缓冲区的c叩acity 可以不相同，说明巳quals（）方法比较的是position
到limit 的内容是否完全一样。
1 ） 验证：判断是不是自身，如果是自身，则返回为true 。

         */
        System.out.println("A=" + byteBuffer.equals(byteBuffer));

        System.out.println("----------判断是不是ByteBuffer 类的实例， 如果不是， 则返回false 。-------------");
        byte[] byteArrayIn1 = {1,2,3,4,5};
        int[] byteArrayIn2 = {1,2,3,4,5};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(byteArrayIn1);
        IntBuffer intBuffer2 = IntBuffer.wrap(byteArrayIn2);

        System.out.println("A="+intBuffer2.equals(byteBuffer));

    }

}
