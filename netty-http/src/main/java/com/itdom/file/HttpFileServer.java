package com.itdom.file;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {

    private static final String DEFAULT_URL = "/netty-http/src/main/java/com/itdom/";

    public void run(final int port, final String url) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            /**
                             * HTTP请求消息解码器
                             */
                            socketChannel.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                            /**
                             * HttpObjectAggregator解码器，它的作用是将多个消息转换伟单一的FullHttpRequest或者FullResponse,原因是HTTP解码器在每个
                             * HTTP消息中会生成多消息对象。
                             * HttpRequest/HttpResponse
                             * HttpContent
                             * LastHttpContent.
                             */
                            socketChannel.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                            /**
                             * HTTP响应消息编码
                             */
                            socketChannel.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                            /**
                             *支持异步发送大码流(大文件传输),但是不占用过多的内存空间，繁殖发生内存溢出的错误.
                             */
                            socketChannel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                            socketChannel.pipeline().addLast(new HttpFileServerHandler(url));

                        }
                    });

            ChannelFuture future = bootstrap.bind("192.168.10.226",port).sync();
            future.channel().closeFuture().sync();

        }catch (Exception e){}finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    public static void main(String[] args) {
        new HttpFileServer().run(8088,DEFAULT_URL);
    }
}
