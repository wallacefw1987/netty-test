package com.fanwei.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-04-23 16:04
 **/
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    //发送给
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + ": 客户端写出数据");
        ByteBuf buffer = getByteBuf(ctx);
        ctx.channel().writeAndFlush(buffer);
    }

    //读取server端信息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       if (msg instanceof ByteBuf){
           ByteBuf byteBuf = (ByteBuf)msg;
           System.out.println(new Date() + " : 客户端读取到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
       }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好，wallacefw".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
