package com.fanwei.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-14 12:01
 **/
public class InboundHandlerB extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InboundHandlerB: " + msg);
        super.channelRead(ctx,msg);
    }
}
