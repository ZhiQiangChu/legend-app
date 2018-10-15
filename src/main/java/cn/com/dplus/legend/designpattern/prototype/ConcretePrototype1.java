package cn.com.dplus.legend.designpattern.prototype;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:44 18-5-21
 * @Modified By:
 */
public class ConcretePrototype1 implements Prototype {
    @Override
    public Object clone() {
        Prototype prototype = new ConcretePrototype1();

        return prototype;
    }

    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        System.out.println(a ^ b);
    }
}
