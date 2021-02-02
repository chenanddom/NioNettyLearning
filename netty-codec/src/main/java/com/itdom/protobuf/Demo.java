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
    /**
     * 执行结果：
     * Before encode req = subReqID: 1
     * userName: "chendom"
     * productName: "Netty \346\235\203\345\250\201\346\214\207\345\215\227"
     * address: "\346\267\261\345\234\263"
     * address: "\345\214\227\346\265\267"
     *
     * After decode:subReqID: 1
     * userName: "chendom"
     * productName: "Netty \346\235\203\345\250\201\346\214\207\345\215\227"
     * address: "\346\267\261\345\234\263"
     * address: "\345\214\227\346\265\267"
     *
     * Assert equal:-->true
     */
}
