package com.itdom;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

/**
 * 在新创建的选择器中， 3 个集合都是空集合
 */
public class NewCreateSelectionAllCollectionEmptyTest {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        Set<SelectionKey> keys = selector.keys();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        System.out.println("key.size()="+keys.size());
        System.out.println("selectionKeys.size()="+selectionKeys.size());

    }
}
