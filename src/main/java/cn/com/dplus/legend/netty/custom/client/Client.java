package cn.com.dplus.legend.netty.custom.client;

import cn.com.dplus.legend.netty.custom.MessageType;
import cn.com.dplus.legend.netty.custom.NettyConstant;
import cn.com.dplus.legend.netty.custom.codec.MessageDecoder;
import cn.com.dplus.legend.netty.custom.codec.MessageEncoder;
import cn.com.dplus.legend.netty.custom.struct.Header;
import cn.com.dplus.legend.netty.custom.struct.Message;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午2:50 18-10-15
 * @Modified By:
 */
public class Client {

    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    // 创建工作线程组
    EventLoopGroup group = new NioEventLoopGroup();


    public void connect(String host, int port) throws Exception {
        try {
            //配置客户端NIO线程组
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MessageDecoder(1024 * 1024 * 5, 4, 4));
                            ch.pipeline().addLast(new MessageEncoder());
                            ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(50));
                            ch.pipeline().addLast("LoginAuthHandler", new LoginAuthReqHandler());
                            ch.pipeline().addLast("HeartBeatHandler", new HeartBeatReqHandler());
                            ch.pipeline().addLast("ClientHandler", new ClientHandler());
                        }
                    });

            // 发起异步连接操作
            ChannelFuture future = b.connect(new InetSocketAddress(host, port), new InetSocketAddress(NettyConstant.LOCALIP, NettyConstant.LOCAL_PORT)).sync();

            // 手动发测试数据,验证是否会产生TCP粘包/拆包情况
//            Channel channel = future.channel();
//            for (int i = 0; i < 500; i++) {
//                Message message = new Message();
//                Header header = new Header();
//                header.setSessionID(1001L);
//                header.setPriority((byte) 1);
//                header.setType(MessageType.SERVICE_REQ.value());
//                message.setHeader(header);
//                message.setBody("我是请求数据" + i);
//            }

            future.channel().closeFuture().sync();
        } finally {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        connect(NettyConstant.REMOTEIP, NettyConstant.PORT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws Exception {
        new Client().connect(NettyConstant.REMOTEIP, NettyConstant.PORT);
    }
}
