package cn.com.dplus.legend.jvm;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午2:49 18-9-19
 * @Modified By:
 */
public class JvmTest {

    private final static int MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
//        while (true) {
//            byte[] obj1 = new byte[1024 * 1024 * 2];
//            byte[] obj2 = new byte[1024 * 1024 * 10];
//            byte[] obj3 = new byte[1024 * 1024 * 20];
//        }


        /**
         *
         * -Xmn16m -Xms30m -Xmx30m -XX:SurvivorRatio=2 -XX:+UseParNewGC -XX:PretenureSizeThreshold=3145728 -XX:-UseTLAB
         * PretenureSizeThreshold=3145728 (3MB) 对象大于3MB时 直接进入老年代
         * Fixed Heap: 30MB
         *       - Survivor * 2: 4MB*2
         *       - Eden: 8MB
         *       -Tenured: 14MB
         */

//        System.gc(); // 尝试清除由检测工具生成的临时对象
//        Thread.sleep(10000L);
//
//        byte[] obj = new byte[1024 * 1024 * 3 + 1];
//        boolean flag = true;
//        while (flag) {
//            Thread.yield();
//        }


        /**
         * 长期存活的对象直接进入到老年代
         * -XX:+PrintGCDetails -Xmn45m -Xms90m -Xmx90m -XX:SurvivorRatio=1 -XX:MaxTenuringThreshold=2 -XX:+UseParNewGC
         * Fixed Heap: 90MB
         *       - Survivor * 2: 15MB * 2
         *       - Eden: 15MB
         *       - Tenured: 45MB
         */
//        System.gc(); // 尝试清除由检测工具生成的临时对象
//        byte[] obj1 = new byte[MB * 2];
//        byte[] obj2 = new byte[MB * 2];
//        byte[] obj3 = new byte[MB * 12];
//        obj3 = null;
//        byte[] obj4 = new byte[MB * 4];
//        byte[] obj5 = new byte[MB * 12];

        /**
         *
         * 动态对象年龄判读
         *
         * -XX:+PrintGCDetails -Xmn45m -Xms90m -Xmx90m -XX:SurvivorRatio=1 -XX:+UseParNewGC
         *
         * Fixed Heap: 90M
         *       - Survivor * 2: 15MB * 2
         *       - Eden: 15MB
         *       - Tenured: 45MB
         */

//        System.gc(); // 尝试清除由检测工具生成的临时对象
//        byte[] obj1 = new byte[4 * MB];
//        byte[] obj2 = new byte[4 * MB];
//        byte[] obj3 = new byte[12 * MB];
//        obj3 = null;
//        byte[] obj4 = new byte[4 * MB];


//        char s = 'c';
//        char upper_s = (char) (s & 0x5f);
//        System.out.println(upper_s);
//        System.out.println((int) 'a');
//        System.out.println((int) 'A');
//        System.out.println(toLowerCase('A'));
        int a = 128;
        int b = 130;
        System.out.println(b ^ a);

    }

    public static boolean isLowerCase(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    public static boolean isUpperCase(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    public static char toLowerCase(char c) {

        return isUpperCase(c) ? (char) (c ^ 0x20) : c;
    }
}
