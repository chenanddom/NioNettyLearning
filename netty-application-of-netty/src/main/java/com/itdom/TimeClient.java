package com.itdom;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.channels.SocketChannel;

public class TimeClient {
        public void connect(int port,String host){
            NioEventLoopGroup group = new NioEventLoopGroup();

            try {
                    Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY,true)
                        .handler(new ChannelInitializer<io.netty.channel.socket.SocketChannel>() {

                            protected void initChannel(io.netty.channel.socket.SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new TimeClientHandler());
                            }
                        });
                //发起异步连接操作
                ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
                channelFuture.channel().closeFuture().sync();
            }catch (Exception e){

            }finally {
                group.shutdownGracefully();
            }
        }

    public static void main(String[] args) {
        new TimeClient().connect(8088,"localhost");
    }
}
