package com.fanwei.netty.client;

import com.fanwei.netty.handler.FirstClientHandler;
import com.fanwei.netty.protocol.PacketCodeC;
import com.fanwei.netty.protocol.request.MessageRequestPacket;
import com.fanwei.netty.protocol.response.MessageResponsePacket;
import com.fanwei.netty.utils.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-04-23 08:13
 **/
public class NettyClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new FirstClientHandler());
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        connect(bootstrap,HOST,PORT,MAX_RETRY);

//        while (true) {
//            channel.writeAndFlush(System.currentTimeMillis() + ": hello world!");
//            Thread.sleep(2000);
//        }
    }

    private static void connect(Bootstrap bootstrap,String host,Integer port,Integer retry){
        bootstrap.connect(host,port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("链接成功！");
                Channel channel = ((ChannelFuture)future).channel();
                //这里通过多线程插入数据
                startConsoleThread(channel);
            }else if (retry == 0){
                System.err.println("重试次数已用完，放弃连接！");
            }else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.err.println(new Date() + ": 链接失败， 第"+order+"次重连。。。。。。");
                bootstrap.config().group().schedule(()->connect(bootstrap,host,port,(retry - 1)),delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(()->{
            while (!Thread.interrupted()){
                if (LoginUtil.hasLogin(channel)){
                    System.out.println("输入消息发送至服务端: ");

                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);

                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(),packet);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }
}
