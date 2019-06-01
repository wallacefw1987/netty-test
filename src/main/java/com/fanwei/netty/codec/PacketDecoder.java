package com.fanwei.netty.codec;

import com.fanwei.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-06-01 01:21
 **/
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //实现数据解密方法
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
