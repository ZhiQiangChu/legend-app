package cn.com.dplus.legend.designpattern.builder;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:34 18-5-21
 * @Modified By:
 */
public class BuilderPatternTest {
    public static void main(String[] args) {
        Director director = new Director();
        Builder builder = new ConcreteBuilder();
        director.construct(builder);
        Computer computer = builder.getComputer();
        computer.show();
    }
}
