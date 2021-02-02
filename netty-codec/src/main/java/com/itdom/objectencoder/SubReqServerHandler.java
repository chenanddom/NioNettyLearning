package com.itdom.objectencoder;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

@ChannelHandler.Sharable
public class SubReqServerHandler extends ChannelHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReq subscribeReq = (SubscribeReq) msg;
        if ("Chendom".equalsIgnoreCase(subscribeReq.getUserName())) {
            System.out.println("service accept client subscribe req:[" + subscribeReq.toString() + "]");

            SubscribeResp resp = new SubscribeResp();
            resp.setSubReqID(subscribeReq.getSubReqID());
            resp.setDesc("Netty book order succed,3 days later,sent to the designated address");
            resp.setRespCode(0);
            ctx.writeAndFlush(resp);
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
