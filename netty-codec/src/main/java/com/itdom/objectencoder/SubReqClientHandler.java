package com.itdom.objectencoder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter {
    public SubReqClientHandler() {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 1000; i++) {
            SubscribeReq subscribeReq = new SubscribeReq();
            subscribeReq.setAddress("深圳是南山区xxxx");
            subscribeReq.setPhoneNumber("1803930xxxx");
            subscribeReq.setProductName("Netty权威指南");
            subscribeReq.setUserName("Chendom");
            subscribeReq.setSubReqID(i);
            ctx.writeAndFlush(subscribeReq);
        }

//        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response:["+msg+"]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
