package org.jboss.netty.example.discard;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


public class TimeServerHandler extends SimpleChannelHandler{

    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e){
        Channel ch = e.getChannel();
        
        ChannelBuffer time = ChannelBuffers.buffer(4);
        time.writeInt( (int)(System.currentTimeMillis()/1000L + 220898800L));
        
        ChannelFuture f = ch.write(time);
        
        f.addListener(new ChannelFutureListener(){
            public void operationComplete(ChannelFuture future) throws Exception {
                Channel channel = future.getChannel();
                channel.close();
            }
        });
    }
    
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e ){
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
