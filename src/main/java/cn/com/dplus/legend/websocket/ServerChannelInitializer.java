package cn.com.dplus.legend.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:52 17-7-5
 * @Modified By:
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("http-codec", new HttpServerCodec()); // 将请求或应答的消息编码或者解码为HTTP消息
        //        ch.pipeline().addLast("decoder", new HttpRequestDecoder()); // 用于解析HTTP请求报文
        //        ch.pipeline().addLast("encoder", new HttpResponseEncoder()); // 用于将response编码成httpresponse报文发送
        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(64 * 1024)); // 将HTTP消息的多个部分组合成一条完整的HTTP消息
        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler()); // 向客户端发送HTML5文件,主要用于支持浏览器和服务端进行websocket通信
        ch.pipeline().addLast("handshake", new WebSocketServerProtocolHandler("", "", true));
        ch.pipeline().addLast("serverhandler", new WebSocketServerHandler()); // 消息的Handler处理类
    }
}
