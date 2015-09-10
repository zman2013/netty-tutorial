package org.jboss.netty.example.time;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


public class UnixTimeClientHandler extends SimpleChannelHandler{

    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e){
        UnixTime time = (UnixTime) e.getMessage();
        System.out.println(time);
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
    
    
}
