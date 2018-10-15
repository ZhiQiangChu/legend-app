package kafka;


import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

import java.util.Properties;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午1:51 18-5-3
 * @Modified By:
 */
public class ProducerTest {
    private final Producer<String, String> producer;
    private final String topic;

    public ProducerTest(String topic, String[] args) {
        // 设置配置属性
        Properties props = new Properties();
        props.put("metadata.broker.list", "10.3.2.20:9092,10.3.2.21:9092,10.3.2.22:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        // key.serializer.class默认为serializer.class
//        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        // 可选配置，如果不配置，则使用默认的partitioner
//        props.put("partitioner.class", "com.catt.kafka.demo.PartitionerDemo");
        // 触发acknowledgement机制，否则是fire and forget，可能会引起数据丢失
        // 值为0,1,-1,可以参考
        //kafka.apache.org/08/configuration.html
        //https://my.oschina.net/cloudcoder/blog/299215
        props.put("request.required.acks", "1");

        producer = new Producer(new kafka.producer.ProducerConfig(props));
        this.topic = topic;
    }

    public void producerMsg() throws InterruptedException {
        int events = 1;
        while (true) {
            try {
                producer.send(new KeyedMessage(topic, events + 1 + " hello"));
                System.out.println("Send complete");
            } catch (Exception e) {
                e.printStackTrace();
            }
            events++;
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerTest producer = new ProducerTest("TOPIC-0002", args);
        producer.producerMsg();
    }

}
