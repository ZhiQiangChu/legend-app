package cn.com.dplus.legend.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:47 18-6-22
 * @Modified By:
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);
        final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        System.out.println("main: before readlock.lock()");
        readLock.lock();
        System.out.println("main:after readlock.lock()");
        Thread tw = new Thread(() -> {
            try {
                System.out.println("tw: before writeLock.lock()");
                writeLock.lock();
                System.out.println("tw: after writeLock.lock()");
            } finally {
                writeLock.unlock();
            }
        });

        Thread tr = new Thread(() -> {
            System.out.println("tr: before readLock.lock()");
            readLock.lock();
            System.out.println("tr: after readLock.lock()");
        });


        try {
            tw.start();
            Thread.sleep(1000);
            tr.start();
            tw.join();
            tr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
