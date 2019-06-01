package com.fanwei.netty.server;

import com.fanwei.netty.codec.PacketDecoder;
import com.fanwei.netty.codec.PacketEncoder;
import com.fanwei.netty.server.handler.LoginRequestHandler;
import com.fanwei.netty.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-04-23 08:06
 **/
public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {

                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());

                        //这个处理器的作用就是负责读取客户端来的数据
//                        ch.pipeline().addLast(new FirstServerHandler());
//                        ch.pipeline().addLast(new InboundHandlerA());
//                        ch.pipeline().addLast(new InboundHandlerB());
//                        ch.pipeline().addLast(new InboundHandlerC());
//
//                        ch.pipeline().addLast(new OutboundHandlerA());
//                        ch.pipeline().addLast(new OutboundHandlerB());
//                        ch.pipeline().addLast(new OutboundHandlerC());


                    }
                });

        bind(serverBootstrap, PORT);
    }

    private static void bind(ServerBootstrap serverBootstrap,int port){
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
