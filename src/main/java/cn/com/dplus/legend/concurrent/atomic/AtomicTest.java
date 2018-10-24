package cn.com.dplus.legend.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午3:08 18-10-23
 * @Modified By:
 */
public class AtomicTest {


    private static Person person;

    private static AtomicReference<Person> atomicPerson;

    public static void main(String[] args) throws InterruptedException {
        person = new Person("Tom", 18);
        atomicPerson = new AtomicReference<>(person);
        System.out.println("Atomic persion is " + atomicPerson.get().toString());

        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Now Atomic Persion is " + atomicPerson.get().toString());
    }

    static class Task1 implements Runnable {

        @Override
        public void run() {
            atomicPerson.getAndSet(new Person("Tom1", atomicPerson.get().getAge() + 1));
            System.out.println("Thread1 Atomic References " + atomicPerson.get().toString());
        }

    }

    static class Task2 implements Runnable {

        @Override
        public void run() {
            atomicPerson.getAndSet(new Person("Tom2", atomicPerson.get().getAge() + 2));
            System.out.println("Thread2 Atomic References " + atomicPerson.get().toString());
        }
    }


}
