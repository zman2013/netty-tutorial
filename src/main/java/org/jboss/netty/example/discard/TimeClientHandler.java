package org.jboss.netty.example.discard;

import java.util.Date;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


public class TimeClientHandler extends SimpleChannelHandler{

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e){
        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
        long currentTimeMillis = buf.readInt() * 1000L;
        System.out.println( new Date(currentTimeMillis));
        e.getChannel().close();
    }
    
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e){
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
