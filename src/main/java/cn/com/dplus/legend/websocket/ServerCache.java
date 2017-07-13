package cn.com.dplus.legend.websocket;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:48 17-7-6
 * @Modified By:
 */
public class ServerCache {
    public static ConcurrentHashMap<String, Channel> channels = new ConcurrentHashMap<String, Channel>();
}
