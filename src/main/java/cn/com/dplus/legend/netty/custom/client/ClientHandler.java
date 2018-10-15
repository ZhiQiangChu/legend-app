package cn.com.dplus.legend.netty.custom.client;

import cn.com.dplus.legend.netty.custom.MessageType;
import cn.com.dplus.legend.netty.custom.struct.Header;
import cn.com.dplus.legend.netty.custom.struct.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:48 18-10-15
 * @Modified By:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        System.err.println("向服务端发送业务数据");
//        Message message = buildReq();
//        ctx.writeAndFlush(message);
//    }

    private Message buildReq() {
        Message message = new Message();
        Header header = new Header();
        header.setType(MessageType.SERVICE_REQ.value());
        message.setHeader(header);
        message.setBody("Hello world");
        return message;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            Message message = (Message) msg;
            if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_RESP.value()) {
                System.err.println("向服务端发送业务数据");
                Message serviceReq = buildReq();
                ctx.writeAndFlush(serviceReq);
            } else if (message.getHeader() != null && message.getHeader().getType() == MessageType.SERVICE_RESP.value()) {
                System.err.println("Client receive message from server:" + message.getBody());
            } else {
                ctx.fireChannelRead(msg);
            }

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("-------客户端数据异常-----");
        ctx.close();
    }

}
