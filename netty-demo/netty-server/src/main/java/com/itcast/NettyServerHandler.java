package com.itcast;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
;import java.util.ArrayList;
import java.util.List;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    public static List<Channel> channelList =new ArrayList<Channel>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channelList.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server--收到消息： "+ msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("server-----读取数据出现异常");
        cause.printStackTrace();
        ctx.close();
    }
}

