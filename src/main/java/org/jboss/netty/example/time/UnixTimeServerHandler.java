package org.jboss.netty.example.time;

import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


public class UnixTimeServerHandler extends SimpleChannelHandler{

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        UnixTime time = new UnixTime( System.currentTimeMillis()/1000);
        ChannelFuture f = e.getChannel().write(time);
        f.addListener(ChannelFutureListener.CLOSE);
    }
    
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e ){
        UnixTimeServer.allChannels.add(e.getChannel());
    }

}
