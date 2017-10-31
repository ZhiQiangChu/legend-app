package cn.com.dplus.legend.rxjava;


import io.reactivex.*;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.*;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:37 17-8-16
 * @Modified By:
 */
public class RXJavaTest {
    //    public static void main(String[] args) {
////        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
////            @Override
////            public void subscribe(FlowableEmitter<String> flowableEmitter) throws Exception {
////                flowableEmitter.onNext("Hello RxJava 2");
////                flowableEmitter.onComplete();
////            }
////        }, BackpressureStrategy.BUFFER);
////    }
////
////    Subscriber subscriber = new Subscriber<String>() {
////
////        @Override
////        public void onSubscribe(Subscription subscription) {
////            System.out.println("onSubscribe");
////            subscription.request(Long.MAX_VALUE);
////        }
////
////        @Override
////        public void onNext(String s) {
////            System.out.println(s);
////        }
////
////        @Override
////        public void onError(Throwable throwable) {
////
////        }
////
////        @Override
////        public void onComplete() {
////            System.out.println("on complete");
////        }
////    };
//
////        Flowable<String> flowable = Flowable.just("Hello RxJava 2");
////        Consumer consumer = new Consumer<String>() {
////            @Override
////            public void accept(String s) throws Exception {
////                System.out.println(s);
////            }
////        };
//
////        flowable.subscribe(consumer);
////        Flowable.just("Hello RxJava 2").subscribe(new Consumer<String>() {
////            @Override
////            public void accept(String s) throws Exception {
////                System.out.println(s);
////            }
////        });
//
////        Flowable.just("hello RxJava2 -ittianyu").subscribe(System.out::print);
//
//        Flowable.just("hello RxJava 2").subscribe(s -> System.out.println(s + "-ittianyu"));
//
//    }
    @Test
    public void testOperator() {

        Flowable.just("map1").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.hashCode();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer.toString();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    @Test
    public void testOperator2() {
        List<Integer> list = asList(10, 1, 5);
//        Flowable.fromIterable(list).subscribe(System.out::println);
        Flowable.just(list).subscribe(nums -> {
            Observable.fromIterable(nums).subscribe(num -> System.out.println(num));
        });
    }

    @Test
    public void testFlatMap() {
        List<Integer> list = asList(10, 1, 5);
        Flowable.just(list).flatMap(new Function<List<Integer>, Publisher<Integer>>() {
            @Override
            public Publisher<Integer> apply(List<Integer> integers) throws Exception {
                return Flowable.fromIterable(integers);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void testFromArray() {
        Flowable.fromArray(1, 20, 5, 0, -1, 8).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer.intValue() > 5;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void testTake() {
        Flowable.fromArray(1, 2, 3, 4).take(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void testLog() {
        Flowable.just(1, 2, 3).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("保存:" + integer);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer); // 每个元素输出前会输出日志
            }
        });
    }

    @Test
    public void testCreate() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("exception:" + (1 / 1));
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribe(new Subscriber<String>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(1);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("on complete");
            }
        });
    }

    @Test
    public void testRx_1() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                Path filePath = Paths.get("/home/sondon/IdeaProjects/dplus-service/legend-app/pom.xml");
                Files.readAllLines(filePath);
                e.onComplete();
            }
        }).subscribe(() -> System.out.println("OK!"), Throwable::printStackTrace);
    }

    @Test
    public void testFromFuture() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        System.out.println("MAIN:" + Thread.currentThread().getId());
        Callable<String> callable = () -> {
            System.out.println("Callable [" + Thread.currentThread().getId() + "]");
            Path filePath = Paths.get("/home/sondon/IdeaProjects/dplus-service/legend-app/pom.xml");
            return Files.readAllLines(filePath).stream().flatMap(s -> Arrays.stream(s.split(""))).count() + "";
        };

        Future<String> future = executor.submit(callable);
        Consumer<String> onNext = v -> System.out.println("consumer[" + Thread.currentThread().getId() + "]:" + v);
        Flowable.fromCallable(callable).subscribe(onNext);
        System.out.println("END");
    }
}