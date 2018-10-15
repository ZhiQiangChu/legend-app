import org.junit.Test;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:01 18-6-27
 * @Modified By:
 */
public class CollectionTest {

    @Test
    public void testNavigableMap() {
        NavigableMap<String, Integer> navigableTreeMap = new TreeMap<>();
        navigableTreeMap.put("aa", 11);
        navigableTreeMap.put("bb", 22);
        navigableTreeMap.put("cc", 33);
        navigableTreeMap.put("dd", 44);
        navigableTreeMap.put("ee", 55);
        navigableTreeMap.put("ff", 55);
        navigableTreeMap.put("gg", 55);

        System.out.println(navigableTreeMap.size());
        System.out.println(navigableTreeMap.ceilingKey("cc"));
        System.out.println(navigableTreeMap.ceilingEntry("cc"));
        System.out.println(navigableTreeMap.descendingMap());
        System.out.println(navigableTreeMap.firstKey());
        System.out.println(navigableTreeMap.headMap("bb"));
        System.out.println(navigableTreeMap.headMap("bb", true));
//        System.out.println(navigableTreeMap.pollFirstEntry());
//        System.out.println(navigableTreeMap.pollLastEntry());
        System.out.println(navigableTreeMap.subMap("aa", true, "dd", true));
        System.out.println(navigableTreeMap.subMap("aa", false, "dd", true));
        System.out.println(navigableTreeMap.subMap("bb", "dd"));
    }

}
