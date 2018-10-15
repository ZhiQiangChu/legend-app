package cn.com.dplus.legend.designpattern.decorator;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:47 18-5-18
 * @Modified By:
 */
public class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("I am a concrete component");
    }
}
