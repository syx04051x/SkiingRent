package com.alphaz.util.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.util.netty
 * User: C0dEr
 * Date: 2017/3/29
 * Time: 下午8:06
 * Description:This is a class of com.alphaz.util.netty
 */
public class NettyServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf buf = (ByteBuf) msg;
        String recieved = getMessage(buf);
        System.out.println("服务器接收到消息：" + recieved);
        BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));

        try {
            ctx.writeAndFlush(getSendByteBuf(strin.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 从ByteBuf中获取信息 使用UTF-8编码返回
     */
    private String getMessage(ByteBuf buf) {

        byte[] con = new byte[buf.readableBytes()];
        buf.readBytes(con);
        try {
            return new String(con, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ByteBuf getSendByteBuf(String message)
            throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        ByteBuf pingMessage = Unpooled.buffer();
        pingMessage.writeBytes(req);

        return pingMessage;
    }
}