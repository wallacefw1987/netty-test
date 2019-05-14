package com.fanwei.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-14 12:01
 **/
public class InboundHandlerC extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHandlerC: " + msg);
        //这里需要使用writeAndFlush方法才能触发pipeline中的其他OutBoundHandler实体类触发write方法
        ctx.channel().writeAndFlush(msg);
    }
}
