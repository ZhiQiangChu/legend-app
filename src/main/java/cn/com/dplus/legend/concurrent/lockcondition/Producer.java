package cn.com.dplus.legend.concurrent.lockcondition;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:53 17-9-19
 * @Modified By:
 */
public class Producer extends Thread {
    private ProducerConsumerImpl producerConsumer;

    public Producer(ProducerConsumerImpl producerConsumer) {
        super("PRODUCER");
        this.producerConsumer = producerConsumer;
    }

    @Override
    public void run() {
        producerConsumer.put();
    }
}
