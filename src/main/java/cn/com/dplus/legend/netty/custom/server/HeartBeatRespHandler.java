package cn.com.dplus.legend.netty.custom.server;

import cn.com.dplus.legend.netty.custom.MessageType;
import cn.com.dplus.legend.netty.custom.struct.Header;
import cn.com.dplus.legend.netty.custom.struct.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午2:33 18-10-15
 * @Modified By:
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println("Receive client heart beat message : ---->" + message);
            Message heartBeat = buildHeartBeat();
            System.out.println("Send heart beat response message to client : --->" + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private Message buildHeartBeat() {

        Message message = new Message();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
