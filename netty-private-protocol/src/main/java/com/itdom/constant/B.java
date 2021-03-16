package com.itdom.constant;

import com.itdom.codec.A;

import java.util.HashMap;

public class B{
public void show(){
    A a = new A();
//    System.out.println(a.a);

    HashMap<String, Object> map = new HashMap<>();


    System.out.println(hash(100));

}
    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        System.out.println(hash(100));

    }
}
