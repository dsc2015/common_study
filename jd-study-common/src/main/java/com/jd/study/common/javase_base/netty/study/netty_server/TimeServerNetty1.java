package com.jd.study.common.javase_base.netty.study.netty_server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @description
 * @author dushuangcheng
 * @create 2017/4/13
 */
public class TimeServerNetty1 {
    public static void main(String[] args) {

    }

    public void bind(int port){
        //NioEventLoop
        EventLoopGroup patent=new NioEventLoopGroup();
        EventLoopGroup child=new NioEventLoopGroup();
        //初始化一个serverBootStrap
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(patent,child).channel(NioServerSocketChannel.class).
                option(ChannelOption.SO_BACKLOG,1024).childHandler(new ChannelInboundHandler() {
            @Override
            public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

            }

            @Override
            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

            }

            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {

            }

            @Override
            public void channelInactive(ChannelHandlerContext ctx) throws Exception {

            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

            }

            @Override
            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

            }

            @Override
            public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {

            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

            }

            @Override
            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

            }

            @Override
            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

            }
        });

    }
}
