package com.itcast;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NettyClientServer {
    // 要请求的服务器的ip地址
    private String ip;
    // 服务取的端口
    private int port;

    public NettyClientServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private void runServer() throws Exception {
        NioEventLoopGroup bossgroup = new NioEventLoopGroup();
        Bootstrap bs = new Bootstrap();

        bs.group(bossgroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //管道注册handler
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //编码通道处理
                        pipeline.addLast("decode", new StringDecoder());
                        //转码通道处理
                        pipeline.addLast("encode", new StringEncoder());

                        // 处理来自服务端的响应信息
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        System.out.println("---------client 启动 ----------");
        ChannelFuture cf = bs.connect(ip, port).sync();

        String reqStr = "客户端发起连接请求";
        Channel channel = cf.channel();
        channel.writeAndFlush(reqStr);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                        String msg = in.readLine();
                        channel.writeAndFlush(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        ;
    }

    public static void main(String[] args) throws Exception {
        new NettyClientServer("127.0.0.1",9911).runServer();
    }

}
