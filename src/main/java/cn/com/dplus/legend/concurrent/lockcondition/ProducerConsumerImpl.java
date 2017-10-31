package cn.com.dplus.legend.concurrent.lockcondition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:52 17-9-19
 * @Modified By:
 */
public class ProducerConsumerImpl {
    private static final int CAPACITY = 10;
    private final Queue queue = new LinkedList();
    private final Random theRandom = new Random();
    private final Lock aLock = new ReentrantLock();
    private final Condition bufferNotFull = aLock.newCondition();
    private final Condition bufferNotEmpty = aLock.newCondition();

    public void put() {
        aLock.lock();
        try {
            while (queue.size() == CAPACITY) {
                System.out.println(Thread.currentThread().getName() + ": Buffer is full,waiting");
                bufferNotEmpty.await();
            }

            int number = theRandom.nextInt();
            boolean isAdded = queue.offer(number);
            if (isAdded) {
                System.out.printf("%s added %d into queue %n", Thread.currentThread().getName(), number);
                System.out.println(Thread.currentThread().getName() + ": Signalling that buffer is no more empty now");
                bufferNotFull.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            aLock.unlock();
        }
    }

    public void get() {
        aLock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println(Thread.currentThread().getName() + ": Buffer is empty,waiting");
                bufferNotFull.await();
            }
            Integer number = (Integer) queue.poll();
            if (number != null) {
                System.out.printf("%s consumed %d from queue %n", Thread.currentThread().getName(), number);
                System.out.println(Thread.currentThread().getName() + ": Signalling that buffer may be empty now");
                bufferNotEmpty.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            aLock.unlock();
        }
    }

    public static void main(String[] args) {
        ProducerConsumerImpl producerConsumer = new ProducerConsumerImpl();
        Producer producer = new Producer(producerConsumer);
        Consumer consumer = new Consumer(producerConsumer);
        producer.start();
        consumer.start();
    }
}
