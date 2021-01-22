package com.itdom.unpackstick;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeServer2 {

    public void bind(int port){
        /**
         * 该线程组用于接收客户端的连接
         */
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        /**
         * 该线程组用于进行SocketChannel的网络读写。
         */
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //netty用于启动NIO服务端的启动类
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChildChannelHandler2());
            //绑定端口
            ChannelFuture future = bootstrap.bind(port).sync();
           //等待服务端监听端口
            future.channel().closeFuture().sync();
        }catch (Exception e){
            //优雅的推出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * I/O时间的处理类ChildChannelHandler
     * 一般用于就日子，对消息进行编解码
     */
    private class ChildChannelHandler2 extends ChannelInitializer<SocketChannel>{

        protected void initChannel(SocketChannel socketChannel) throws Exception {
            /**
             * LineBasedFrameDecoder的工作原理是它依次遍历ByteBuf中的可读字节，判断是否有"\n"或者"\r\n"，如果有，就以此位置座位结束位置
             * 从可读索引到结束位置区间的字节就组成了一行。它是以换行符为结束标志的解码器，支持携带结束符或者不懈怠结束符连着解码方式，同时支持
             * 配置单行的最大长度。如果连续读取到做大长度后仍然没有发现换行符，就会抛出异常，同时忽略之前读取到的异常麻溜
             */
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            /**
             * StringDecoder的功能非常简单，就是将要接收到的兑现给转换成字符串，然后继续调用后面的handler.
             * lineBasedFrameDecoder+StringDecoder组合就是按照行的切换的文本解码器，它被涉及成为支持TCP的粘包拆包
             */
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new TimeServerHandler2());
        }
    }

    public static void main(String[] args) {
        new TimeServer2().bind(8088);
    }

}
