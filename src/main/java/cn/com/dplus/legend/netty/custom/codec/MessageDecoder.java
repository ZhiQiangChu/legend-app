package cn.com.dplus.legend.netty.custom.codec;

import cn.com.dplus.legend.netty.custom.struct.Header;
import cn.com.dplus.legend.netty.custom.struct.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.apache.commons.collections.map.HashedMap;

import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:09 18-10-12
 * @Modified By:
 */
public class MessageDecoder extends LengthFieldBasedFrameDecoder {


    private MarshallingDecoder marshallingDecoder;

    /**
     * @param maxFrameLength    最大的序列化长度 1024*1025*5
     * @param lengthFieldOffset 长度属性的偏移量(message中总长度的起始位置(Header中length属性的起始位置) 本例为4)
     * @param lengthFieldLength
     */
    public MessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        marshallingDecoder = new MarshallingDecoder();

    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        // 1 调用父类(LengthFieldBasedFrameDecoder)方法
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }

        Message message = new Message();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());

        int size = frame.readInt();
        // 附件个数大于0, 则需要解码操作
        if (size > 0) {
            Map<String, Object> attch = new HashedMap(size);
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i = 0; i < size; i++) {
                keySize = frame.readInt();
                keyArray = new byte[keySize];
                frame.readBytes(keyArray);
                key = new String(keyArray, "UTF-8");
                attch.put(key, marshallingDecoder.decode(frame));
            }
            keyArray = null;
            key = null;
            header.setAttachment(attch);
        }
        message.setHeader(header);
        // 对于ByteBuf来说,读一个数据就会少一个数据,所以读完header剩下的就是body了
        if (frame.readableBytes() > 4) {// 大于4个字节说明肯定有数据了
            message.setBody(this.marshallingDecoder.decode(frame));
        }
        return message;
    }
}
