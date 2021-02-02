package com.itdom.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqOuterClass.SubscribeReq req = (SubscribeReqOuterClass.SubscribeReq)msg;
        if ("chendom".equalsIgnoreCase(req.getUserName())){
            System.out.println("service accept client subscribe req:["+req.toString()+"]");

            SubscribeRespOuterClass.SubscribeResp.Builder builder = SubscribeRespOuterClass.SubscribeResp.newBuilder();
            builder.setRespCode(0);
            builder.setSubReqID(req.getSubReqID());
            builder.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
            ctx.writeAndFlush(builder.build());
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
