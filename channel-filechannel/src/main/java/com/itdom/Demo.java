package com.itdom;

import com.sun.corba.se.impl.orbutil.HexOutputStream;

import java.nio.channels.AsynchronousChannel;
import java.nio.channels.Channel;
import java.nio.channels.InterruptibleChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Au toC ! oseab le 接口的作用是可以自动关闭，而不需要显式地调用close（）方法，
 * A utoClo se abl e 接口强调的是与try （）结合实现自动关闭，该接口针对的是任何资源，不仅仅是1/0 ，
 * 因此， void close （） 方法抛出Exception 异常。该接口不要求是幕等的， 也就是
 * 重复调用此接口的close（）方法会出现副作用。
 */
public class Demo implements AutoCloseable{
    private String name;
    private Integer age;

    public Demo() {
    }

    public String getName() {
        return name;
    }

    public Demo setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Demo setAge(Integer age) {
        this.age = age;
        return this;
    }

   /* public static void main(String[] args) {


        List<Demo> demos = new ArrayList<Demo>(){{
           add(new Demo().setAge(10).setName("zhangsan"));
           add(new Demo().setAge(20).setName("zhangsan"));
           add(new Demo().setAge(20).setName("lisi"));
        }};

        Map<String, List<Demo>> collect = demos.parallelStream().collect(groupingBy(Demo::getName));
        collect.forEach((k,v)->{
            System.out.println("name:"+k+" size:"+v.size());
        });


//        try( Demo demo = new Demo();){
//            System.out.println("测试AutoCloseable接口");
//        }catch (Exception e){
//        e.printStackTrace();
//        }
//        AsynchronousChannel

//        InterruptibleChannel
    }*/

    public void close() throws Exception {
        System.out.println("自动关闭资源...");
    }


    public static void main(String[] args) {
        String content = "this is a demoahdajshdajsdhaskjdhajsdhasjk";
        System.out.println(strTo16(content));
    }
    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

}
