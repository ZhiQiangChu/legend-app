package cn.com.dplus.legend.designpattern.prototype;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:46 18-5-21
 * @Modified By:
 */
public class ConcretePrototype2 implements Prototype {
    @Override
    public Object clone() {
        Prototype prototype = new ConcretePrototype2();
        return prototype;
    }
}
