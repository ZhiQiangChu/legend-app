package cn.com.dplus.legend.designpattern.decorator;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:47 18-5-18
 * @Modified By:
 */
public class Decorator implements Component {
    /**
     * 具体的组件
     */
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
