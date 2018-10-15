package cn.com.dplus.legend.utils;

import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:49 18-5-30
 * @Modified By:
 */
public class commonlang {
    @Test
    public void testRandom() {
        System.out.println(RandomUtils.nextInt(1, 1000));

    }

    @Test
    public void testDateFormat() {
        String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        System.out.println(dateStr);
    }

    @Test
    public void testSystemUtils() {

        System.out.println(SystemUtils.getJavaHome());
        System.out.println(SystemUtils.getUserDir());
    }

    @Test
    public void testWordUtils() {
        System.out.println(WordUtils.initials("hello world"));
        System.out.println(WordUtils.swapCase("hello world"));
    }

    @Test
    public void testArrayCopy() {
        String[] elements = new String[]{"a", "b", "c"};
        String[] copyOf = Arrays.copyOf(elements, 1);
        System.out.println(copyOf);
    }

    @Test
    public void testEnum() {
        double x = 2;
        double y = 4;
        for (Operation o : Operation.values()) {
            System.out.println(String.format("%f %s %f = %f", x, o, y, o.apply(x, y)));
        }
    }
}
