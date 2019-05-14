package com.fanwei.netty.protocol.command;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-10 10:08
 **/
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;

}
