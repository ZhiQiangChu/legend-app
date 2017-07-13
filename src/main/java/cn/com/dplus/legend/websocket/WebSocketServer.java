package cn.com.dplus.legend.websocket;

import cn.com.dplus.legend.application.Application;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;


/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:42 17-7-5
 * @Modified By:
 */
@Component
public class WebSocketServer implements Runnable {


    @Override
    public void run() {
        Thread.currentThread().setName("WebSocket_thread");
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() + 1);
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new ServerChannelInitializer());
        ChannelFuture future = null;
        try {
            future = bootstrap.bind(Application.ws_port).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
