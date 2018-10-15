package cn.com.dplus.legend.netty.custom;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:26 18-10-15
 * @Modified By:
 */
public enum MessageType {
    /**
     * 业务请求消息
     */
    SERVICE_REQ((byte) 0),

    /**
     * 业务响应消息
     */
    SERVICE_RESP((byte) 1),


    /**
     * 业务ONE_WAY消息(既是请求消息又是响应消息)
     */
    ONE_WAY((byte) 2),

    /**
     * 握手请求消息
     */
    LOGIN_REQ((byte) 3),

    /**
     * 握手响应消息
     */
    LOGIN_RESP((byte) 4),

    /**
     * 心跳请求消息
     */
    HEARTBEAT_REQ((byte) 5),


    /**
     * 心跳响应消息
     */
    HEARTBEAT_RESP((byte) 6);


    private byte value;

    private MessageType(byte value) {
        this.value = value;
    }

    public byte value() {
        return this.value;
    }
}
