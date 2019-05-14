package com.fanwei.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-14 12:01
 **/                                //ChanneloutBoundHandlerAdapter
public class OutboundHandlerA extends ChannelOutboundHandlerAdapter  {


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutBoundHandlerA: " + msg);
        super.write(ctx, msg, promise);
    }
}
