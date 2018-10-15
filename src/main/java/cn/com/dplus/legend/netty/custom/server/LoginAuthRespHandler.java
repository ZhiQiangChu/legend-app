package cn.com.dplus.legend.netty.custom.server;

import cn.com.dplus.legend.netty.custom.MessageType;
import cn.com.dplus.legend.netty.custom.ResultType;
import cn.com.dplus.legend.netty.custom.struct.Header;
import cn.com.dplus.legend.netty.custom.struct.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午5:11 18-10-12
 * @Modified By:
 */
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {

    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<>();

    private String[] whitekList = {"127.0.0.1", "192.168.56.1"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        Message message = (Message) msg;
        // 如果是握手请求消息,处理,其他消息,透传
        if (message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_REQ.value()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            Message loginResp;

            //重复登录,拒绝
            if (nodeCheck.containsKey(nodeIndex)) {
                System.out.println("重复登录,拒绝请求");
                loginResp = buildResponse(ResultType.FAIL);
            } else {
                InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOK = false;
                for (String WIP : whitekList) {
                    if (WIP.equals(ip)) {
                        isOK = true;
                        break;
                    }
                }
                loginResp = isOK ? buildResponse(ResultType.SUCCESS) : buildResponse(ResultType.FAIL);
                if (isOK) {
                    nodeCheck.put(nodeIndex, true);
                }
            }

            System.out.println("The login response is : " + loginResp);
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }


    }

    private Message buildResponse(ResultType resultType) {
        Message message = new Message();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.value());
        message.setHeader(header);
        message.setBody(resultType.value());
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        nodeCheck.remove(ctx.channel().remoteAddress().toString());
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }
}
