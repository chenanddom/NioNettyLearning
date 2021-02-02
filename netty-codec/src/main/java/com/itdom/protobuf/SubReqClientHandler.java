package com.itdom.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {

            SubscribeReqOuterClass.SubscribeReq.Builder builder = SubscribeReqOuterClass.SubscribeReq.newBuilder();
            builder.setUserName("chendom");
            builder.setProductName("Netty 权威指南");
            builder.setSubReqID(i);
            List<String> addressArray = new ArrayList<>();
            addressArray.add("sz");
            addressArray.add("bh");
            builder.addAllAddress(addressArray);
            ctx.write(builder.build());
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client receive msg:["+msg+"]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
