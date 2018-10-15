package cn.com.dplus.legend.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:32 18-5-17
 * @Modified By:
 */
public class CglibProxy implements MethodInterceptor {
    private Object target;

    public Object getProxyInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before target method...");
        Object result = methodProxy.invoke(this.target, args);
        System.out.println("After target method...");
        return result;
    }

    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        Hello hello = (Hello) proxy.getProxyInstance(new HelloImpl());
        String s = hello.sayHello("Green");
        System.out.println(s);
        UserDao userDao = (UserDao) proxy.getProxyInstance(new UserDaoImpl());
        userDao.login("green", "12345");
        System.out.println(userDao.getClass().getName());
    }
}
