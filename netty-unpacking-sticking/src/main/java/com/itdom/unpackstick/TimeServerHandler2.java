package com.itdom.unpackstick;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeServerHandler2 extends ChannelHandlerAdapter {
        private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf)msg;
//        byte[] req = new byte[buf.readableBytes()];
//        buf.readBytes(req);
//        String body = new String(req,"utf-8");

//        String body = new String(req,"UTF-8").substring(0,req.length-System.getProperty("line.separator").length());
//
//        System.out.println("The time server receive order:"+body);
//        String currentTime = "QUERT TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
//       ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        String body = (String)msg;
        System.out.println("The time server receive order:"+body+" ;the counter is:"+ ++counter);
       String currentTime = "QUERT TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
       currentTime=currentTime+System.getProperty("line.separator");
       ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
        /**
         * 将msg转换成Netty的ByteBuf对象。ByteBuf类似的JDK的ByteBuffer对象，不过他提供了更加强大和灵活的的功能。通过ByteBuf的
         * readableBytes方法可以获取缓冲区的中的字节数，根据可读的字节数创建byte数组，通过ByteBuf的readByte方法将缓冲区中的字节数组复制打屁新建的
         * byte数组中，最后通过ByteBuf的readBytes方法将缓冲区中的字节数组复制到新建的byte数组中，最后通过new String()构造函数获取消息。
         * 并且对获取到的消息进行判断，最后将响应通过ChannelHandlerContext的write方法异步的发神银行大消息给客户端。
         */
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        /**
         * 将消息发送队列中的消息写入打屁SocketChannel中发送。从性能方面考虑，Netty并不直接将消息写入SocketChannel中，调用write
         * 方法只是把待发送的消息放到发送缓冲区数组中，在通过调用flush方法，将消息全部写到SocketChannel中。
         */
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
