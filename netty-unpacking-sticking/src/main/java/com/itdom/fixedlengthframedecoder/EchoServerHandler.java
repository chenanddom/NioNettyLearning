package com.itdom.fixedlengthframedecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler  extends ChannelHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive client: ["+msg+"]");
        System.out.println("This is "+ ++counter+" times receive client:["+msg+"]");
        ByteBuf byteBuf = Unpooled.copiedBuffer(((String)msg).getBytes());
        ctx.writeAndFlush(byteBuf);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
