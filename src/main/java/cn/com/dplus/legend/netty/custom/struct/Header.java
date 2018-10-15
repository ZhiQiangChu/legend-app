package cn.com.dplus.legend.netty.custom.struct;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午1:12 18-10-12
 * @Modified By:
 */
@Data
@ToString
public class Header {
    //唯一的通信标志
    private int crcCode = 0xadaf0105;

    // 总消息长度
    private int length;

    // 回话ID
    private long sessionID;

    // 消息类型
    private byte type;

    // 消息的优先级
    private byte priority;

    //附件
    private Map<String, Object> attachment = new HashedMap();
}
