package com.fanwei.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-14 15:08
 **/
public class OutboundHandlerC extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutboundHandlerC: " + msg);
        super.write(ctx, msg, promise);
    }
}
