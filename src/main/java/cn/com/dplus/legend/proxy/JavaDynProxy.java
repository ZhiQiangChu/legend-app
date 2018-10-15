package cn.com.dplus.legend.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:22 18-5-17
 * @Modified By:
 */
public class JavaDynProxy implements InvocationHandler {
    private Object target;

    public Object getProxyInstance(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("Before target method...");
        result = method.invoke(target, args);
        System.out.println("After target method...");
        return result;
    }

    public static void main(String[] args) {
        JavaDynProxy javaDynProxy = new JavaDynProxy();
        Hello hello = (Hello) javaDynProxy.getProxyInstance(new HelloImpl());
        String s = hello.sayHello("Green");
        System.out.println(s);
        UserDao userDao = (UserDao) javaDynProxy.getProxyInstance(new UserDaoImpl());
        userDao.login("green", "12345");

        System.out.println(userDao.getClass().getName());

    }
}
