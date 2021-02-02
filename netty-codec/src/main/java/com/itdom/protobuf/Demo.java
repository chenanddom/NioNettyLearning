package com.itdom.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqOuterClass.SubscribeReq.Builder builder = SubscribeReqOuterClass.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("chendom");
        builder.setProductName("Netty 权威指南");
        List<String> address = new ArrayList<>();
        address.add("深圳");
        address.add("北海");
        builder.addAllAddress(address);
        SubscribeReqOuterClass.SubscribeReq req = builder.build();
        System.out.println("Before encode req = "+req.toString());


        SubscribeReqOuterClass.SubscribeReq subscribeReq = SubscribeReqOuterClass.SubscribeReq.parseFrom(req.toByteArray());
        System.out.println("After decode:"+subscribeReq.toString());
        System.out.println("Assert equal:-->"+subscribeReq.equals(req));
    }
}
