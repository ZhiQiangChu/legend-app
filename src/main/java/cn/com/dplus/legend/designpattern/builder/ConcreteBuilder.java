package cn.com.dplus.legend.designpattern.builder;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:29 18-5-21
 * @Modified By:
 */
public class ConcreteBuilder extends Builder {

    Computer computer = new Computer();

    @Override
    public void buildCPU() {
        computer.add("CPU");
    }

    @Override
    public void buildMainboard() {
        computer.add("主板");
    }

    @Override
    public void buildHD() {
        computer.add("硬盘");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
