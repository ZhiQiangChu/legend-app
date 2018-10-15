package cn.com.dplus.legend.proxy;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:17 18-5-17
 * @Modified By:
 */
public class HelloImpl implements Hello {
    @Override
    public String sayHello(String name) {
        String s = "Hello, " + name;
        System.out.println(this.getClass().getName() + "->" + s);
        return s;
    }
}
