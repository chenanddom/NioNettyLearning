package com.itdom.xml.server;

import com.itdom.xml.codec.HttpXmlRequestDecoder;
import com.itdom.xml.codec.HttpXmlResponseEncoder;
import com.itdom.xml.pojo.Order;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HttpXmlServer {
    public static void main(String[] args) {
        new HttpXmlServer().run(8088);
    }
    public void  run(final int port){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                            socketChannel.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65535));
                            socketChannel.pipeline().addLast("xml-decoder",new HttpXmlRequestDecoder(Order.class,true));
                            socketChannel.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                            socketChannel.pipeline().addLast("xml-encoder",new HttpXmlResponseEncoder());
                            socketChannel.pipeline().addLast("xmlServerHandler",new HttpXmlServerHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("HTTP订购服务器启动，网址是："+"http://localhost:"+port);
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){}finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
