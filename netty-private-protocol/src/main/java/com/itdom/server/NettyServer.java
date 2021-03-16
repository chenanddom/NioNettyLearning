package com.itdom.server;

import com.itdom.codec.NettyMessageEncoder;
import com.itdom.constant.NettyConstant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NettyServer {
    private static final Log logger = LogFactory.getLog(NettyServer.class);
    public void bind(){
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyMessageEncoder());
                    }
                });
        bootstrap.bind(NettyConstant.REMOTEIP,NettyConstant.PORT);
        logger.info("Netty server start ok:"+(NettyConstant.REMOTEIP+":"+NettyConstant.PORT));
    }
    public static void main(String[] args) {
        new NettyServer().bind();
    }
}
