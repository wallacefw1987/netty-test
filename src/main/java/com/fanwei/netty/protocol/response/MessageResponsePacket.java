package com.fanwei.netty.protocol.response;

import com.fanwei.netty.protocol.Packet;
import lombok.Data;

import static com.fanwei.netty.protocol.command.Command.MESSAGE_RESPONSE;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-10 16:08
 **/
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
