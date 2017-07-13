package cn.com.dplus.legend.websocket;

import cn.com.dplus.legend.application.Application;
import cn.com.dplus.project.utils.LogUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;


/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:43 17-7-5
 * @Modified By:
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler {

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Http 接入,WebSocket第一次连接使用http连接,用于握手
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }


    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // Upgrade:websocket
        if (!req.decoderResult().isSuccess() || !"websocket".equals(req.headers().get("Upgrade"))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req), null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            wsFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            ChannelFuture handshakeFuture = handshaker.handshake(ctx.channel(), req);
            handshakeFuture.addListener((ChannelFutureListener) future -> {
                if (!future.isSuccess()) {
                    ctx.fireExceptionCaught(future.cause());
                } else {
                    //用于激活握手已经完成的事件,可以让用户的代码收到通知
                    ctx.fireUserEventTriggered(WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE);
                }
            });
        }

    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse resp) {
        //返回应答给客户端
        if (resp.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(resp.status().toString(), CharsetUtil.UTF_8);
            resp.content().writeBytes(buf);
            buf.release();
        }
        ChannelFuture f = ctx.channel().writeAndFlush(resp);
        // 如果非Keep-Alive,关闭连接
        if (!isKeepAlive(req) || resp.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }

    }

    private boolean isKeepAlive(FullHttpRequest req) {
        return false;
    }

    private String getWebSocketLocation(FullHttpRequest req) {
        return "ws://127.0.0.1:" + Application.ws_port + "/ws";
    }

    /**
     * @Description:
     * @Author: 詹军政|zhanjz@sondon.net
     */
    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {

        // 收到关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            ServerCache.channels.remove(ctx.channel().id());//从缓存中删除该channel
        }
        // 收到ping消息,给它回复pong
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
        }

        // 本例仅支持文本消息,不支持二进制消息
        if (frame instanceof BinaryWebSocketFrame) {
            throw new UnsupportedOperationException(String.format("%s frame type not supported", frame.getClass().getName()));
        }

        if (frame instanceof TextWebSocketFrame) {
            //  缓存channel 用于向客户端发送消息
            //返回应答消息
            String recvMsg = ((TextWebSocketFrame) frame).retain().text();
            LogUtil.Info("服务端收到:" + recvMsg);
            ctx.channel().write(new TextWebSocketFrame("服务端已收到消息[" + recvMsg + "]"));

        }
    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ServerCache.channels.put(ctx.channel().id().asLongText(), ctx.channel()); // 缓存channel
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ServerCache.channels.remove(ctx.channel().id().asLongText()); // 删除该channel缓存
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
