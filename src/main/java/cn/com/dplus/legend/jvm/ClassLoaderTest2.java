package cn.com.dplus.legend.jvm;

import java.io.InputStream;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午5:20 18-10-17
 * @Modified By:
 */
public class ClassLoaderTest2 {

    public static void main(String[] args) throws Exception {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(filename);
                if (is == null) {
                    return super.loadClass(name);
                }

                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (Exception e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj = myClassLoader.loadClass("cn.com.dplus.legend.jvm.ClassLoaderTest2").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof cn.com.dplus.legend.jvm.ClassLoaderTest2);
    }
}
