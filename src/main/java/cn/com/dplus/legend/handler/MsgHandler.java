package cn.com.dplus.legend.handler;

import cn.com.dplus.legend.websocket.ServerCache;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午11:13 17-7-7
 * @Modified By:
 */
@Component
public class MsgHandler {
    public void publishMsg(String content) {
        Iterator itor = ServerCache.channels.entrySet().iterator();
        while (itor.hasNext()) {//给每个在线的channel发送消息
            Map.Entry<String, Channel> entry = (Map.Entry) itor.next();
            Channel channel = entry.getValue();
            if (channel.isActive()) {
                channel.writeAndFlush(new TextWebSocketFrame(content));
            }
        }
    }
}
