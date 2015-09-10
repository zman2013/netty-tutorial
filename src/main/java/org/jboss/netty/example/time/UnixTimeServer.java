package org.jboss.netty.example.time;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;


public class UnixTimeServer {
    
    static final ChannelGroup allChannels = new DefaultChannelGroup("time-server");

    public static void main(String[] args) throws InterruptedException{
        
        ChannelFactory factory = new NioServerSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool());
        
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory(){
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(
                        new UnixTimeEncoder(),
                        new UnixTimeServerHandler());
            }});
        Channel channel = bootstrap.bind(new InetSocketAddress(8080));
        
        allChannels.add(channel);
        Thread.sleep(10000);
        ChannelGroupFuture future = allChannels.close();
        future.awaitUninterruptibly();
        factory.releaseExternalResources();
        
    }
}
