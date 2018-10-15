package cn.com.dplus.legend.designpattern.prototype;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:47 18-5-21
 * @Modified By:
 */
public class Client {
    public static void main(String[] args) {
        Prototype prototype1 = new ConcretePrototype1();
        ConcretePrototype1 concretePrototype1 = (ConcretePrototype1) prototype1.clone();
        Prototype prototype2 = new ConcretePrototype2();
        ConcretePrototype2 concretePrototype2 = (ConcretePrototype2) prototype2.clone();
    }
}
