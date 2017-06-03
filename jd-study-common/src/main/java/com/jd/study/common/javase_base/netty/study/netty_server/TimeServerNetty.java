package com.jd.study.common.javase_base.netty.study.netty_server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Date;


/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/11
 */
public class TimeServerNetty {
    public static void main(String[] args) {
        new TimeServerNetty().bind(8080);
    }

    public void bind(int port) {
        //reactor线程组
        //一个用于接收客户端的请求
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        //一个用于处理网路的读写事件
        EventLoopGroup childGroup = new NioEventLoopGroup();
        //NIO服务端启动的辅助类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //1,设置并绑定reactor线程池
        //2，设置并绑定服务端的channel NioServerSocketChannel
        //3，TCP连接建立时创建ChannelPipeline
        //4，设置并添加channelHandler
        //5,绑定监听端口，并启动服务器
        //6，selector轮询
        //7,网络事件通知
        //8，channelPipeline去执行ChannelHandler
        serverBootstrap.group(parentGroup, childGroup).channel(NioServerSocketChannel.class).
                option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChildChannelHandler());
        //绑定端口
        try {
            ChannelFuture sync = serverBootstrap.bind(port).sync();
            //d等待服务器端口的关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭资源并退出
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }

    }

    class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new TimeServerNettyHandler());
        }
    }

    class TimeServerNettyHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf readBuffer = (ByteBuf) msg;
            byte[] bytes = new byte[readBuffer.readableBytes()];
            readBuffer.readBytes(bytes);

            String queryString = new String(bytes);
            System.out.println("the query string is:" + queryString);
            //回复客户端现在的时间
            String currentTime = new Date(System.currentTimeMillis()).toString();
            ByteBuf byteBuf = Unpooled.copiedBuffer(currentTime.getBytes());
            ctx.write(byteBuf);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
}
