package com.alphaz.util.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.util.netty
 * User: C0dEr
 * Date: 2017/3/29
 * Time: 下午8:05
 * Description:This is a class of com.alphaz.util.netty
 */
public class NettyServerBootstrap {

    private int port;

    public NettyServerBootstrap(int port) {
        this.port = port;
        bind();
    }

    private void bind() {

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024); //连接数
            bootstrap.option(ChannelOption.TCP_NODELAY, true);  //不延迟，消息立即发送
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true); //长连接
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel)
                        throws Exception {
                    ChannelPipeline p = socketChannel.pipeline();
                    p.addLast(new NettyServerHandler());
                }
            });
            ChannelFuture f = bootstrap.bind(port).sync();
            if (f.isSuccess()) {
            }
            // 关闭连接
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        NettyServerBootstrap server = new NettyServerBootstrap(9999);

    }
}
