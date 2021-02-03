package com.itdom.marshalling;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq)msg;
        System.out.println("service accept client subscribe req:[" + req.toString() + "]");
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(req.getSubReqID());
        resp.setDesc("Netty book order succed,3 days later,sent to the designated address");
        resp.setUserName(req.getUserName());
        resp.setRespCode(0);
        ctx.writeAndFlush(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
