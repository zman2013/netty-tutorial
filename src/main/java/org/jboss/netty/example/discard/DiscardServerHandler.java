package org.jboss.netty.example.discard;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


public class DiscardServerHandler extends SimpleChannelHandler{

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e){
        Channel channel = e.getChannel();
        channel.write(e.getMessage());
    }
    
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e){
        e.getCause().printStackTrace();
        
        Channel ch = e.getChannel();
        ch.close();
    }
}
