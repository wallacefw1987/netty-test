package com.fanwei.netty.protocol;


import com.fanwei.netty.protocol.request.LoginRequestPacket;
import com.fanwei.netty.serialize.Serializer;
import com.fanwei.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-12 10:31
 **/
public class PacketCodeCTest {

    @Test
    public void encode(){
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion((byte)1);
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("wallace");
        loginRequestPacket.setPassword("123456");

        PacketCodeC packetCodeC = PacketCodeC.INSTANCE;
        ByteBuf byteBuf = packetCodeC.encode(ByteBufAllocator.DEFAULT.ioBuffer(),loginRequestPacket);
        Packet decodePacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket),serializer.serialize(decodePacket));
//        System.out.println(serializer.serialize(loginRequestPacket.equals(serializer.serialize(decodePacket))));
    }

}