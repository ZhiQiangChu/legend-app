package cn.com.dplus.legend.netty.custom.server;

import cn.com.dplus.legend.netty.custom.struct.Header;
import cn.com.dplus.legend.netty.custom.struct.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午5:19 18-10-12
 * @Modified By:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("------通道激活------");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message requestMessage = (Message) msg;
        System.err.println("Server receive message from client: " + requestMessage.getBody());
        Message responseMessage = new Message();
        Header header = new Header();
        header.setSessionID(2002L);
        header.setPriority((byte) 2);
        header.setType((byte) 1);
        responseMessage.setHeader(header);
        responseMessage.setBody("我是响应数据: " + requestMessage.getBody());
        ctx.writeAndFlush(responseMessage);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("------数据读取完毕------");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("------数据读取异常------");
        cause.printStackTrace();
        ctx.close();
    }
}
