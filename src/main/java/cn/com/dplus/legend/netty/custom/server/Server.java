package cn.com.dplus.legend.netty.custom.server;

import cn.com.dplus.legend.netty.custom.NettyConstant;
import cn.com.dplus.legend.netty.custom.codec.MessageDecoder;
import cn.com.dplus.legend.netty.custom.codec.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午1:21 18-10-12
 * @Modified By:
 */
public class Server {

    public static void main(String[] args) throws Exception {
        // ONE:
        // 1.用于接受客户端连接的线程工作组
        EventLoopGroup boss = new NioEventLoopGroup();
        // 2.用于对接受客户端连接读写操作的线程工作组
        EventLoopGroup worker = new NioEventLoopGroup();


        // TWO:
        // 3.辅助类,用于帮助我们创建Netty服务
        ServerBootstrap b = new ServerBootstrap();
        b.group(boss, worker) //绑定两个工作线程组
                .channel(NioServerSocketChannel.class) // 设置NIO模式
                .option(ChannelOption.SO_BACKLOG, 1024) // 设置TCP缓冲区
                .option(ChannelOption.SO_RCVBUF, 32 * 1024) // 设置接受数据的缓存大小
                .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE) // 设置保持连接
                .childOption(ChannelOption.SO_SNDBUF, 32 * 1024); // 设置发送数据的缓存大小

        // 初始化绑定服务通道
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel sc) throws Exception {
                sc.pipeline().addLast(new MessageDecoder(5 * 1024 * 1024, 4, 4));
                sc.pipeline().addLast(new MessageEncoder());
                sc.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                sc.pipeline().addLast("LoginAuthHandler", new LoginAuthRespHandler());
                sc.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());
                sc.pipeline().addLast(new ServerHandler());
            }
        });

        ChannelFuture cf = b.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();
        System.out.println("Netty server start ok : " + (NettyConstant.REMOTEIP + ":" + NettyConstant.PORT));
        //释放连接
        cf.channel().closeFuture().sync();
        worker.shutdownGracefully();
        boss.shutdownGracefully();
    }
}
