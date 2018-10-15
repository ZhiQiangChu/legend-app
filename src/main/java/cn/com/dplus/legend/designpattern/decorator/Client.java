package cn.com.dplus.legend.designpattern.decorator;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:56 18-5-18
 * @Modified By:
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Component decoratorA = new ConcreteDecoratorA(component);
        decoratorA.operation();
        Decorator decoratorB = new ConcreteDecoratorB(decoratorA);
        decoratorB.operation();
    }
}
