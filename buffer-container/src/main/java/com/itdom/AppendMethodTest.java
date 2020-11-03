package com.itdom;

import java.nio.CharBuffer;

/**
 * public CharBuffer append( char c）方法的作用： 将指定字符添加到此缓冲区（可选操作） 。
 * 调用此方法的形式为dst.append(c） ，该调用与以下调用完全相同： dst.put(c） 。
 * public CharBuffer append(CharSequence csq) 方法的作用：将指定的字符序列添加到此
 * 缓冲区（可选操作） 。调用此方法的形式为dst.append(csq），该调用与以下调用完全相同：
 * dst. put( csq. toStringO) ，有可能没有添加整个序列，这取决于针对字符序列csq 的to String 规
 * 范。例如，调用字符缓冲区的to String（）方法将返回一个子序列，其内容取决于缓冲区的位
 * 置和限制。
 * public CharBuffer append(CharSequence csq, int sta此， int end）方法的作用：将指定字符
 * 序列的子序列添加到此缓冲区（可选操作） 。当csq 不为null 时，调用此方法的形式为dst.
 * append(c吨， sta此， end），该调用与以下调用完全相同： dst.put( csq. sub Sequence( start, end).
 * to String（）） 。
 */
public class AppendMethodTest {
    public static void main(String[] args) {
        CharBuffer charBuffer = CharBuffer.allocate(15);
        System.out.println("A "+charBuffer.position());
        charBuffer.append('a');
        System.out.println("B "+charBuffer.position());
        charBuffer.append("bcdefg");
        System.out.println("C "+charBuffer.position());
        /*
          将字符串子序列添加到缓冲区
         */
        charBuffer.append("abchijklmn",3,8);
        System.out.println("D "+charBuffer.position());

        char[] array = charBuffer.array();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
        System.out.println("charbuffer.capacity()="+charBuffer.capacity());

    }
}
