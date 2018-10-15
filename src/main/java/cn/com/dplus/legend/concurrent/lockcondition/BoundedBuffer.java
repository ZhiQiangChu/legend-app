package cn.com.dplus.legend.concurrent.lockcondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:44 18-5-30
 * @Modified By:
 */
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    /** 写条件 */
    final Condition notFull = lock.newCondition();
    /** 读条件 */
    final Condition notEmpty = lock.newCondition();

    final Integer[] items = new Integer[10];

    /** 写索引 */
    int putptr;

    /** 读索引 */
    int takeptr;

    /**
     * 队列中存在的数据个数
     */
    int count;


    /**
     * 写
     *
     * @param x
     */
    public void put(Integer x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
                System.out.println("阻塞写线程");
            }
            items[putptr] = x;
            // 写满了 putptr置为0
            if (++putptr == items.length) {
                putptr = 0;
            }
            count++;
            notEmpty.signal();
            System.out.println("唤醒读线程");
        } finally {
            lock.unlock();
        }
    }



    /**
     * 读
     *
     * @return
     */
    public Integer take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
                System.out.println("阻塞读线程");
            }
            Integer x = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            count--;
            notFull.signal();
            System.out.println("唤醒写线程");
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        Thread putThread = new Thread(() -> {
            int i = 0;
            while (true) {
                try {
                    boundedBuffer.put(i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        putThread.start();
        Thread takeThread = new Thread(() -> {
            while (true) {
                try {
                    Integer x = boundedBuffer.take();
                    System.out.println(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        takeThread.start();
    }
}
