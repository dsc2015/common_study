package com.jd.study.common.javase_base.netty.study.netty_client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author dushuangcheng
 * @description
 * @create 2017/4/11
 */
public class TimeClientNetty {
    public static void main(String[] args) {
        new TimeClientNetty().connect("127.0.0.1",8080);

    }

    public void connect(String host, int port)  {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new TimeClientNettyHandler());
            }
        });

        ChannelFuture future = null;
        try {
            future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }

    }

    class TimeClientNettyHandler extends ChannelInboundHandlerAdapter {
        private final ByteBuf byteBuf;

        public TimeClientNettyHandler() {
            String message="query the time:";
            System.out.println(message);
            this.byteBuf = Unpooled.copiedBuffer(message.getBytes());
            byteBuf.writeBytes(message.getBytes());
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(byteBuf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf1= (ByteBuf) msg;
            byte[] bytes = new byte[byteBuf1.readableBytes()];
            byteBuf1.readBytes(bytes);
            String response=new String(bytes);
            System.out.println("the server response is now :"+response);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    class TimeClientNettyHandler1 extends ChannelHandlerAdapter {
        private final ByteBuf byteBuf;

        public TimeClientNettyHandler1() {
            String message="query the time:";
            System.out.println(message);
            this.byteBuf = Unpooled.copiedBuffer(message.getBytes());
            byteBuf.writeBytes(message.getBytes());
        }

      /*  @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(byteBuf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf1= (ByteBuf) msg;
            byte[] bytes = new byte[byteBuf1.readableBytes()];
            byteBuf1.readBytes(bytes);
            String response=new String(bytes);
            System.out.println("the server response is now :"+response);
        }
*/
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
}
