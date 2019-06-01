package com.fanwei.netty.server.handler;

import com.fanwei.netty.protocol.request.LoginRequestPacket;
import com.fanwei.netty.protocol.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;

/**
 * @program: netty-test
 * @description 登陆接受信息地方 对应 ServerHandler 这个方法
 * @author: fanwei
 * @create: 2019-06-01 09:54
 **/
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        if (valid(msg)){
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": 登录成功!");
        }else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println(new Date() + ": 登录失败!");
        }
        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    //这里是校验msg数据
    private boolean valid(LoginRequestPacket msg) {
        return true;
    }
}
