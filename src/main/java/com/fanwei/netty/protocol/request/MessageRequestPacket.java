package com.fanwei.netty.protocol.request;

import com.fanwei.netty.protocol.Packet;
import lombok.Data;

import static com.fanwei.netty.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-10 16:05
 **/
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
