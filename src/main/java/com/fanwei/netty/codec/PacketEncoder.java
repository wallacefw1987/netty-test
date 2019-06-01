package com.fanwei.netty.codec;

import com.fanwei.netty.protocol.Packet;
import com.fanwei.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-06-01 14:44
 **/
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out,msg);
    }
}
