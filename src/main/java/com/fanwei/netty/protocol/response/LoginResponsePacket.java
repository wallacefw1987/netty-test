package com.fanwei.netty.protocol.response;

import com.fanwei.netty.protocol.Packet;
import lombok.Data;

import static com.fanwei.netty.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-10 16:02
 **/
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
