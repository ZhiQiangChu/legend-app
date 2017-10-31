package cn.com.dplus.legend.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午8:12 17-9-18
 * @Modified By:
 */
public class LockTest {
    static class NumberWrapper {
        public int value = 1;
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition reachThreeCond = lock.newCondition();
        Condition reachSixCond = lock.newCondition();
        final NumberWrapper num = new NumberWrapper();
        // 123 , 456
        Thread A = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Thread A start to print...");
                while (num.value <= 3) {
                    System.out.println(num.value);
                    num.value++;
                }
                // 告诉B线程可以开始了
                reachThreeCond.signal();
            } finally {
                lock.unlock();
            }
            try {
                lock.lock();
                // 等待6
                reachSixCond.await();
                System.out.println("Thread A start to print...");
                while (num.value <= 9) {
                    System.out.println(num.value);
                    num.value++;
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread B = new Thread(() -> {
            lock.lock();
            try {
                while (num.value <= 3) {
                    reachThreeCond.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            try {
                lock.lock();
                System.out.println("Thread B start to print...");
                while (num.value <= 6) {
                    System.out.println(num.value);
                    num.value++;
                }
                // reach 6
                reachSixCond.signal();
            } finally {
                lock.unlock();
            }
        });

        A.start();
        B.start();
    }
}
