package cn.com.dplus.legend.netty.custom.codec;

import cn.com.dplus.legend.netty.custom.struct.Header;
import cn.com.dplus.legend.netty.custom.struct.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午1:32 18-10-12
 * @Modified By:
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {

    private MarshallingEncoder marshallingEncoder;

    public MessageEncoder() throws IOException {
        marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf sendBuf) throws Exception {
        if (message == null || message.getHeader() == null) {
            throw new Exception("编码失败,没有数据信息!");
        }

        //Head:
        Header header = message.getHeader();
        sendBuf.writeInt(header.getCrcCode());
        sendBuf.writeInt(header.getLength());
        sendBuf.writeLong(header.getSessionID());
        sendBuf.writeByte(header.getType());
        sendBuf.writeByte(header.getPriority());
        // 对附件信息进行编码
        // 编码规则为:如果attachment的长度为0,表示没有可选附件,则将长度编码设置为0
        // 如果attachment长度大于0,则需要编码,规则:
        // 首先对附件的个数进行编码
        //附件大小
        sendBuf.writeInt(header.getAttachment().size());
        String key = null;
        byte[] keyArray = null;
        Object value = null;
        // 然后对key进行编码,先编码长度,然后再将它转化为byte数组之后编码内容
        for (Map.Entry<String, Object> param : header.getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(value, sendBuf);
        }

        key = null;
        keyArray = null;
        value = null;
        //Body:
        Object body = message.getBody();
        if (body != null) {
            this.marshallingEncoder.encode(body, sendBuf);
        } else {
            // 如果没有数据则进行补位,为了方便后续的decoder操作
            sendBuf.writeInt(0);
        }
        // 最后我们要获取整个数据包的总长度 也就是header + body进行header length的设置
        // TODO: 解释:这里必须要-8个字节,是因为CRC和长度本身占的减掉了
        // (官方中给出的是: LengthFieldBasedFrameDecoder中的lengthFieldOffset+lengthFieldLength)
        // 总长度是在header协议的第二个标记字段中
        // 第一个参数是长度属性的索引位置
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }
}
