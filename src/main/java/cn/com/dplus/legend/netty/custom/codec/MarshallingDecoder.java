package cn.com.dplus.legend.netty.custom.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

/**
 * @Description: 根据编码规则去解码
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:15 18-10-12
 * @Modified By:
 */
public class MarshallingDecoder {

    private Unmarshaller unmarshaller;

    public MarshallingDecoder() throws IOException {
        this.unmarshaller = MarshallingCodeCFactory.buildUnMarshalling();
    }

    public Object decode(ByteBuf in) throws Exception {
        try {
            // 1 首先读取4个字节长度(body内容长度)
            int bodySize = in.readInt();
            // 2 获取实际body的缓冲内容
            int readerIndex = in.readerIndex();
            ByteBuf buf = in.slice(readerIndex, bodySize);
            // 3 转换
            ChannelBufferByteInput input = new ChannelBufferByteInput(buf);
            // 4 读取操作
            this.unmarshaller.start(input);
            Object ret = this.unmarshaller.readObject();
            this.unmarshaller.finish();
            // 5 读取完毕后,更新当前读取起始位置
            // 因为使用slice方法,原buf的位置还在readerIndex上,故需要将位置重新设置一下
            in.readerIndex(in.readerIndex() + bodySize);
            return ret;
        } finally {
            this.unmarshaller.close();
        }
    }
}
