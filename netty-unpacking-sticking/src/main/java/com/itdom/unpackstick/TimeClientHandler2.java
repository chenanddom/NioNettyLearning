package com.itdom.unpackstick;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler2 extends ChannelHandlerAdapter{

//    private final ByteBuf firstMessage;
    private byte[] req;

    private int counter;

    public TimeClientHandler2() {
          req = ("QUERT TIME ORDER"+System.getProperty("line.separator")).getBytes();
//        byte[] req = "QUERT TIME ORDER".getBytes();
//       firstMessage = Unpooled.buffer(req.length);
//       firstMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
           ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf)msg;
//        byte[] req = new byte[buf.readableBytes()];
//         buf.readBytes(req);
//        String body = new String(req, "UTF-8");
//        System.out.println("Now is:"+body);
        System.out.println("Now is :"+(String)msg+" ;the counter is:"+ ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
