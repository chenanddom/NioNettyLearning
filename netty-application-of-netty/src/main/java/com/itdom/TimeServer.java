package com.itdom;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {

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
                .childHandler(new ChildChannelHandler());
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
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) {
        new TimeServer().bind(8088);
    }

}
