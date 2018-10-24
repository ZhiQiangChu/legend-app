package cn.com.dplus.legend.jvm;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午5:02 18-10-17
 * @Modified By:
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        try {
            System.out.println(System.getProperty("java.class.path"));
            Class typeLoaded = Class.forName("cn.com.dplus.legend.jvm.TestBean");
            System.out.println(typeLoaded.getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
