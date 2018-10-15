package cn.com.dplus.legend.guava;

import com.google.common.base.*;
import com.google.common.cache.*;
import com.google.common.collect.*;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:03 17-11-17
 * @Modified By:
 */

public class GuavaTest {

    @Test
    public void pageInfoTest() {
        int recordSize = 20;
        int pageSize = 10;
        int totalPageNum = (recordSize + pageSize - 1) / pageSize;
        System.out.println("Total page nums:" + totalPageNum);
    }


    @Test
    public void fileTest() {
        File file = new File("/home/sondon/hs_err_pid2439.log");
        System.out.println(Files.getFileExtension(file.getName()));
        System.out.println(Files.getNameWithoutExtension(file.getName()));

        try {
            List<String> lines = Files.readLines(file, Charsets.UTF_8, new LineProcessor<List<String>>() {
                List<String> results = new ArrayList<>();

                @Override
                public boolean processLine(String line) throws IOException {
                    results.add("======>" + line);
                    return true;
                }

                @Override
                public List<String> getResult() {
                    return results;
                }
            });
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUrlEncoder() {
        String fileName = "ND#223";
        try {
            System.out.println(URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOptional() {
        Optional<Integer> possible = Optional.of(6);
        if (possible.isPresent()) {
            System.out.println(possible.isPresent());
            System.out.println(possible.get());
        }
    }

    @Test
    public void testEventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new Object() {
            @Subscribe
            public void handleUserInfoChangeEvent(UserInfoChangeEvent userInfoChangeEvent) {
                System.out.println("处理用户信息变化事件：" + userInfoChangeEvent.getUserName());
            }

            @Subscribe
            public void handleUserInfoChangeEvent(BaseEventBusEvent baseEventBusEvent) {
                System.out.println("所有事件的父类");
            }

        });

        eventBus.post(new UserInfoChangeEvent("apple"));
    }


    @Test
    public void testAsyncEventBus() {
        Executor executor = Executors.newFixedThreadPool(10);
        AsyncEventBus asyncEventBus = new AsyncEventBus("asyncEventBus", executor);
        asyncEventBus.register(new Object() {
            @Subscribe
            public void handleUserInfoChangeEvent(UserInfoChangeEvent userInfoChangeEvent) {
                System.out.println("处理用户信息变化事件：" + userInfoChangeEvent.getUserName());
            }

            @Subscribe
            public void handleUserInfoChangeEvent(BaseEventBusEvent userInfoChangeEvent) {
                System.out.println("所有事件的父类");
            }
        });

        asyncEventBus.post(new UserInfoChangeEvent("apple"));
        System.out.println("异步EventBus");
    }

    static class BaseEventBusEvent {

    }

    static class UserInfoChangeEvent extends BaseEventBusEvent {
        private String userName;

        public UserInfoChangeEvent(String userName) {
            this.userName = userName;
        }

        public String getUserName() {
            return userName;
        }
    }


    @Test
    public void testSplitter() {

        Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split("foo,bar,, qux");
        split.forEach(System.out::println);
    }

    @Test
    public void testTicker() {
        TestTicker testTicker = new TestTicker();
        Cache<String, byte[]> cache = CacheBuilder.newBuilder().ticker(testTicker).expireAfterWrite(1, TimeUnit.HOURS)
                .build();
        cache.put("id", new byte[1024 * 1024]);
        testTicker.addElapsedTime(TimeUnit.NANOSECONDS.convert(1, TimeUnit.HOURS));
        System.out.println(cache.getIfPresent("id") == null);
    }

    static class TestTicker extends Ticker {
        private long start = Ticker.systemTicker().read();
        private long elapsedNano = 0;

        @Override
        public long read() {
            return start + elapsedNano;
        }

        public void addElapsedTime(long elapsedNano) {
            this.elapsedNano = elapsedNano;
        }
    }


    @Test
    public void testCache() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).refreshAfterWrite(10, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                String strProValue = "hello " + key + "!";
                return strProValue;
            }
        });

        try {
            System.out.println(cache.get("jerry"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCallablex() {
        TestTicker testTicker = new TestTicker();
        Cache<String, User> cache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(5, TimeUnit.SECONDS).recordStats()
                .removalListener(new RemovalListener<String, User>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, User> notification) {
                        System.out.println("Cause:" + notification.getCause() + " k:" + notification.getKey() + " v:" + notification.getValue() + "===被移除");
                    }
                })
                .ticker(testTicker)
                .build();
        cache.put("1", new User("1", "Zhang San"));
        cache.put("2", new User("2", "Li Si"));
        ConcurrentMap<String, User> concurrentMap = cache.asMap();
        System.out.println(concurrentMap);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        cache.invalidateAll();
        CacheStats stats = cache.stats();
        System.out.println("hit rate:" + stats.hitRate());
        System.out.println("average load penalty:" + stats.averageLoadPenalty());
        System.out.println("eviction count:" + stats.evictionCount());

    }

    @Data
    @AllArgsConstructor
    private static class User {
        private String id;
        private String name;
    }

    @Test
    public void testCompareAndSet() {
        AtomicInteger i = new AtomicInteger(1);
        i.set(2);
        int next = 3;
        if (i.compareAndSet(2, next)) {
            i.set(next);
        }
    }

    @Test
    public void testCaseFormat() {
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, "CONSTANT_NAME"));
    }

    @Test
    public void testStopWatch() {
        System.out.println("StopWatch Start ");
        int countingDown = 10;
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.elapsed(TimeUnit.SECONDS);
        stopwatch.start();
        while (countingDown > 0) {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("StopWatch Is Running ? --> " + stopwatch.isRunning());
            System.out.println("StopWatch Time is " + stopwatch);
            countingDown--;
        }
        System.out.println("End Of Looping");
        System.out.println("Check Total Time is Needed: " + stopwatch);

        stopwatch.stop();
        System.out.println(stopwatch.isRunning());
    }

    @Test
    public void testMultiset() {
        HashMultiset<Object> multiset = HashMultiset.create();
        multiset.add("a", 3);
        multiset.add("b", 5);
        multiset.add("c", 1);
        ImmutableMultiset<Object> highestCountFirst = Multisets.copyHighestCountFirst(multiset);
        highestCountFirst.stream().forEach(System.out::println);
    }

    @Test
    public void testMultimaps() {
        ImmutableSet<String> digits = ImmutableSet.of("zero", "one", "two", "three", "four",
                "five", "six", "seven", "eight", "nine");
        Function<String, Integer> lengthFunction = new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        };

        ImmutableListMultimap<Integer, String> digitsByLength = Multimaps.index(digits, lengthFunction);
        System.out.println(digitsByLength);

    }

    @Test
    public void testFile() {
        File file = new File("/home/sondon/ssl.key");
//        try {
//            ImmutableList<String> readLines = Files.asCharSource(file, Charsets.UTF_8).readLines();
//            readLines.stream().forEach(System.out::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(Files.getFileExtension("/home/sondon/ssl.key"));
    }

    @Test
    public void testLockCondition() {
    }

}
