package cn.com.dplus.legend.designpattern.builder;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:24 18-5-21
 * @Modified By:
 */
public class Director {
    public void construct(Builder builder) {
        builder.buildCPU();
        builder.buildMainboard();
        builder.buildHD();
    }
}
