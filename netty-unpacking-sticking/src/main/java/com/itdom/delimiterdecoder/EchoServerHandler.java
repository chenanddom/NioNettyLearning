package com.itdom.delimiterdecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler extends ChannelHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /**
         * 本例程使用"$_"作为分隔符，而且分隔符在传递到服务端之后就回被DelimiterBasedFrameDecoder分隔符解码器
         * 将分隔符"$_"去掉。String在收到数据后将缓冲区的数据提取出来转换成字符串。为了返回给客户端的数据也能够正常接收
         * 需要将分隔符"$_"加上。
         */
        String body = (String)msg;
        System.out.println("This is "+ ++counter+" times receive client:["+body+"]");
        body+="$_";
        ByteBuf byteBuf = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
