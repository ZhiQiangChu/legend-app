package cn.com.dplus.legend.rxjava;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午5:00 18-5-22
 * @Modified By:
 */
public class Visit implements Runnable {
    private AtomicBoolean someone = new AtomicBoolean();


    @Override
    public void run() {
        if (someone.compareAndSet(false, true)) {
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                    + " i am coming");
            System.out.println(Thread.currentThread().getName()
                    + " i have to leaving");
            someone.set(false);
        } else {
            System.out.println(Thread.currentThread().getName()
                    + " i can't visit the house");
        }
    }

    public static void main(String[] args) {
        Visit st = new Visit();
        new Thread(st, "thread_1").start();
        new Thread(st, "thread_2").start();
    }
}
