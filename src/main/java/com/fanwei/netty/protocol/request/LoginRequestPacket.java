package com.fanwei.netty.protocol.request;

import com.fanwei.netty.protocol.Packet;
import lombok.Data;

import static com.fanwei.netty.protocol.command.Command.LOGIN_REQUEST;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-10 10:09
 **/
@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
