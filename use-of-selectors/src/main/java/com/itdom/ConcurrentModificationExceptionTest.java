package com.itdom;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 多线程环境下删除键集中的键会导致ConcurrentModificationException 异常
 * 一般情况下，选择器的键和巳选择键集由多个并发线程使用是不安全的。如果这样的
 * 线程可以直接修改这些键集之一，那么应该通过对该键集本身进行同步来控制访问。这些
 * 键集的it巳rator（）方法所返回的迭代器是快速失败的：如果在创建迭代器后以任何方式（调
 * 用迭代器自身的remove（）方法除外）修改键集，则会抛出ConcurrentModificationException
 * 异常。
 */
public class ConcurrentModificationExceptionTest {
    public static void main(String[] args) throws InterruptedException {
        Set set = new HashSet<>();
        set.add("abc1");
        set.add("abc2");
        set.add("abc3");
        set.add("abc4");
        set.add("abc5");
        set.add("abc6");
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    set.remove("abc3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Thread.sleep(1000);
            iterator.next();
        }
        /**
         * Exception in thread "main" java.util.ConcurrentModificationException
         * 	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1445)
         * 	at java.util.HashMap$KeyIterator.next(HashMap.java:1469)
         * 	at com.itdom.ConcurrentModificationExceptionTest.main(ConcurrentModificationExceptionTest.java:40)
         */
    }
}
