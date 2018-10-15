package cn.com.dplus.legend.designpattern.builder;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:22 18-5-21
 * @Modified By:
 */
public abstract class Builder {
    /**
     * 装CPU
     */
    public abstract void buildCPU();

    /**
     * 装主板
     */
    public abstract void buildMainboard();

    /**
     * 装硬盘
     */
    public abstract void buildHD();

    /**
     * 获得组装好的电脑
     *
     * @return
     */
    public abstract Computer getComputer();
}
