package cn.com.dplus.legend.netty.custom.struct;

import lombok.Data;
import lombok.ToString;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午1:16 18-10-12
 * @Modified By:
 */
@Data
@ToString(exclude = {"body"})
public final class Message {
    private Header header;

    private Object body;
}
