package cn.com.dplus.legend.netty.custom.codec;

import org.jboss.marshalling.*;

import java.io.IOException;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:59 18-10-12
 * @Modified By:
 */
public final class MarshallingCodeCFactory {

    /**
     * 创建JBoss Marshaller编码对象
     *
     * @return
     * @throws IOException
     */
    public static Marshaller buildMarshalling() throws IOException {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        Marshaller marshaller = marshallerFactory.createMarshaller(configuration);
        return marshaller;
    }

    /**
     * 创建Jboss Unmarshaller解码对象
     *
     * @return
     * @throws IOException
     */
    public static Unmarshaller buildUnMarshalling() throws IOException {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        Unmarshaller unmarshaller = marshallerFactory.createUnmarshaller(configuration);
        return unmarshaller;
    }


}
