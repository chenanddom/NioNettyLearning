package com.itdom.protobuf;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class SubReqClient {

    public void connect(int port,String host){

        NioEventLoopGroup group = new NioEventLoopGroup();
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                          socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                          socketChannel.pipeline().addLast(new ProtobufDecoder(SubscribeRespOuterClass.SubscribeResp.getDefaultInstance()));
                          socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                          socketChannel.pipeline().addLast(new ProtobufEncoder());
                          socketChannel.pipeline().addLast(new SubReqClientHandler());

                        }
                    });
            /**
             * 发起异步连接操作
             */
            ChannelFuture future = bootstrap.connect(host, port).sync();
            /**
             * 等待了护短链路关闭
             */
            future.channel().closeFuture().sync();
        }catch (Exception e){

        }finally {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new SubReqClient().connect(8088,"localhost");
    }
}
