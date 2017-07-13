package cn.com.dplus.legend.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午2:12 17-7-13
 * @Modified By:
 */
public class Boss extends Thread {
    private String name;
    private CountDownLatch countDownLatch;

    public Boss(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Boss[" + name + "] is begin working");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Boss[" + name + "] begin checking the workers' work");
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        executor.submit(new Boss("Bill Gates", countDownLatch));
        executor.submit(new Worker("A", countDownLatch));
        executor.submit(new Worker("B", countDownLatch));
        executor.submit(new Worker("C", countDownLatch));
        executor.shutdown();
    }
}
