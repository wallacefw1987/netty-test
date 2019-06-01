package com.fanwei.netty.server;

import com.fanwei.netty.protocol.Packet;
import com.fanwei.netty.protocol.PacketCodeC;
import com.fanwei.netty.protocol.request.LoginRequestPacket;
import com.fanwei.netty.protocol.request.MessageRequestPacket;
import com.fanwei.netty.protocol.response.LoginResponsePacket;
import com.fanwei.netty.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-12 14:53
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf decodeByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(decodeByteBuf);

        if (packet instanceof LoginRequestPacket){
            System.out.println(new Date() + ": 收到客户端登录请求……");
            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(loginRequestPacket.getVersion());
            // 登录校验
            if (valid(loginRequestPacket)) {
                // 校验成功
                loginResponsePacket.setSuccess(true);
            } else {
                // 校验失败
                loginResponsePacket.setReason("账号或密码错误");
                loginResponsePacket.setSuccess(false);
            }
            //编码
            ByteBuf responseBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().ioBuffer(),loginResponsePacket);
            ctx.channel().writeAndFlush(responseBuf);
        }else if (packet instanceof MessageRequestPacket){
            //强转MessageRequestPacket
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket)packet;

            MessageResponsePacket responsePacket = new MessageResponsePacket();
            System.out.println(new Date() + ": 收到客户端消息：" + messageRequestPacket.getMessage());
            responsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
            ByteBuf responseByte = PacketCodeC.INSTANCE.encode(ctx.alloc().ioBuffer(),responsePacket);
            ctx.channel().writeAndFlush(responseByte);
        }
    }

    //这里作为校验账号与密码
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
