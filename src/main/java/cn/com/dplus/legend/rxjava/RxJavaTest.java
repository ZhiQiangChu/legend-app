package cn.com.dplus.legend.rxjava;


import com.google.common.base.CaseFormat;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:12 18-5-21
 * @Modified By:
 */
public class RxJavaTest {

    /**
     * 简单观察者模式，没有背压
     */
    @Test
    public void testSimpleObserver() {
        Observable<Integer> mObservable = Observable.create(e -> {
            e.onNext(1);
            e.onNext(2);
            e.onError(new IllegalArgumentException("Error"));
            e.onComplete();
        });

        Observer mObserver = new Observer<Integer>() {

            //这是新加入的方法，在订阅后发送数据之前，
            //回首先调用这个方法，而Disposable可用于取消订阅
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(d.isDisposed());

            }

            @Override
            public void onNext(Integer value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }


            @Override
            public void onComplete() {
                System.out.println("Have Done!");
            }
        };

        mObservable.subscribe(mObserver);

    }

    @Test
    public void testCreateObservableAndObserver() {
        // Observable的创建
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                // 执行一些其他操作
                // TODO
                // 执行完毕，触发回调，通知观察者
                System.out.println("我来发射数据");
                e.onNext("abc");
            }
        });

        // Observer的创建
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                System.out.println("我接收到数据了:" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        // 订阅
        observable.subscribe(observer);

    }

    @Test
    public void testJust() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("Hello " + i);
        }
        Observable<String> observable = Observable.fromIterable(list);
        // Observer的创建
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                System.out.println("我接收到数据了:" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("全部接收完成");
            }
        };

        observable.subscribe(observer);
    }

    private void subscribe(Observable observable) {
        // Observer的创建
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                System.out.println("我接收到数据了:" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("全部接收完成");
            }
        };

        observable.subscribe(observer);
    }

    @Test
    public void testDefer() {
        Observable<String> observable = Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just("Hello");
            }
        });
        subscribe(observable);
    }

    @Test
    public void testInterval() {
        Observable<Long> observable = Observable.interval(2, TimeUnit.SECONDS);
        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        observable.subscribe(observer);

    }


    @Test
    public void testRange() {
        Observable<Integer> observable = Observable.range(1, 20);
        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                System.out.println(value);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    @Test
    public void testTimer() {
        Observable<Long> observable = Observable.timer(2, TimeUnit.SECONDS);
        observable.subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {

                System.out.println("Hello");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Test
    public void testMap() {
        Observable<Integer> observable = Observable.just("hello", "you").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        });

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    @Test
    public void testFlatMap() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        Observable<Object> observable = Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        });
        observable.subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object value) {
                System.out.println(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Test
    public void testFilter() {
        List<String> list = Arrays.asList("a", "bfg", "cfg", "d", "e");
        Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Exception {
                String newStr = (String) o;
                if (newStr.contains("fg")) {
                    return true;
                }
                return false;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println((String) o);
            }
        });
    }

    @Test
    public void testTake() {
        List<String> list = Arrays.asList("a", "bfg", "cfg", "d", "e");
        Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).take(3).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println((String) o);
            }
        });
    }

    @Test
    public void testDoOnNext() {
        List<String> list = Arrays.asList("a", "bfg", "cfg", "d", "e");
        Observable.just(list).flatMap(new Function<List<String>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).take(5).doOnNext(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("准备工作");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println((String) o);
            }
        });
    }


}
