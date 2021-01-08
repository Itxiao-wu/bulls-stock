package com.itcast;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NettyProviderServer {
    private int port;

    public NettyProviderServer(int port) {
        this.port = port;
    }
    // netty 服务端启动
    public void runServer() throws Exception{
        // 用来接收进来的连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 用来处理已经被接收的连接，一旦bossGroup接收到连接，就会把连接信息注册到workerGroup上
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        // nio服务的启动类
        try {
            ServerBootstrap sbs = new ServerBootstrap();
            // 配置nio服务参数
            sbs.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //管道注册handler
                             ChannelPipeline pipeline = socketChannel.pipeline();
                            //编码通道处理
                            pipeline.addLast("decode",new StringDecoder());
                            //转码通道处理
                            pipeline.addLast("encode",new StringEncoder());

                            // 处理接收到的请求
                            pipeline.addLast(new NettyServerHandler());// 这里相当于过滤器，可以配置多个


                        }
                    });
            System.err.println("------------server 启动---------");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                            String str =in.readLine();
                            if (NettyServerHandler.channelList.size() > 0) {
                                for (Channel channel : NettyServerHandler.channelList) {
                                    channel.writeAndFlush(str);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            ChannelFuture cf =sbs.bind(port).sync();
            cf.channel().closeFuture().sync();
        } finally {
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception{
        new NettyProviderServer(9911).runServer();
    }
}
