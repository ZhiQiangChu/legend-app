package cn.com.dplus.legend.netty.custom;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:34 18-10-15
 * @Modified By:
 */
public enum ResultType {

    /**
     * 认证成功
     */
    SUCCESS((byte) 0),

    /**
     * 认证失败
     */
    FAIL((byte) -1),;

    private byte value;

    private ResultType(byte value) {
        this.value = value;
    }

    public byte value() {
        return this.value;
    }
}
