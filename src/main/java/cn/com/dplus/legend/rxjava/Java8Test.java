package cn.com.dplus.legend.rxjava;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午5:18 17-8-17
 * @Modified By:
 */
public class Java8Test extends TestCase {

    private List<Artist> artists = new ArrayList<>();

    @Override
    protected void setUp() throws Exception {
        artists.addAll(Arrays.asList(new Artist("John Lennon", "London", 56),
                new Artist("Paul McCartney", "London", 60),
                new Artist("George Harrison", "New York", 68),
                new Artist("John Lennon", "Syney", 78),
                new Artist("Ringo starr", "London", 56)));
    }

    @Test
    public void testGroupBy() {
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );
        Map<String, Long> counting = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.counting()));
        System.out.println(counting);
        Map<String, Integer> sum = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        System.out.println(sum);
    }

    @Test
    public void testFilter() {
        long count = artists.stream().filter(artist -> artist.isFrom("London")).count();
        System.out.println(count);
    }

    @Test
    public void testFlatMap() {
        List<Artist> collect = Stream.of(Arrays.asList(new Artist[]{new Artist("Paul McCartney", "London", 60), new Artist("George Harrison", "New York", 68)}),
                Arrays.asList(new Artist[]{new Artist("Paul McCartney", "London", 60), new Artist("John Lennon", "Syney", 78)}))
                .flatMap(a -> a.stream()).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void testFlatMap_2() {
        Student obj1 = new Student();
        obj1.setName("mkyong");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");
        List<Student> students = Arrays.asList(obj1, obj2);
        students.stream().map(Student::getBook).flatMap(x -> x.stream()).distinct().forEach(System.out::println);
    }


    @Test
    public void testReduce() {

    }
}
