package cn.com.dplus.legend.scrab;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午9:40 17-8-16
 * @Modified By:
 */
public class AreaConstants {
    public static final String BASEURL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/";
    public static Map<Integer, String> class_tags = new HashMap<>();

    static {
        class_tags.put(1, "citytr");
        class_tags.put(2, "countytr");
        class_tags.put(3, "towntr");
        class_tags.put(4, "villagetr");
    }
}
