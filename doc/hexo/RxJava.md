# RxJava2响应式编程

RxJava 2.0 最核心的是Publisher和Subscriber。Publisher可以发出一系列的事件，而Subscriber负责和处理这些事件。

平常用得最多的Publisher是Flowable，它支持背压，教程刚开始不适合介绍太多概念，有兴趣的可以看一下 RxJava 2.0中backpressure(背压)概念的理解。
要使用RxJava 2，你需要先引入相应的jar包。

```xml
<dependencies>
    <dependency>
        <groupId>io.reactivex.rxjava2</groupId>
        <artifactId>rxjava</artifactId>
        <version>2.0.1</version>
    </dependency>
    <dependency>
        <groupId>org.reactivestreams</groupId>
        <artifactId>reactive-streams</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

