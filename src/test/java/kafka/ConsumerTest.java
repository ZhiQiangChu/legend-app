package kafka;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午1:51 18-5-3
 * @Modified By:
 */
public class ConsumerTest {

    String zooKeeper = "10.3.2.20:2181,10.3.2.21:2181,10.3.2.22:2181";
    String groupId = "myApp";
    String topic = "TOPIC-0002";

     ConsumerConnector consumerConnector;

    public ConsumerTest() {

        Properties props = new Properties();
        props.put("zookeeper.connect", zooKeeper);
        props.put("group.id", groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
//        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.commit.enable", "false");

        ConsumerConfig config = new ConsumerConfig(props);
        consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(config);
        Map<String, Integer> topicCountMap = new HashMap<>();
        topicCountMap.put(topic, new Integer(1));
        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
        Map<String, List<KafkaStream<String, String>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);

        List<KafkaStream<String, String>> streams = consumerMap.get(topic);

        for (KafkaStream<String, String> stream : streams) {
            StreamConsumer streamConsumer = new StreamConsumer(stream);
            new Thread(streamConsumer).start();
        }
    }


    class StreamConsumer implements Runnable {

        private KafkaStream stream;

        public StreamConsumer(KafkaStream stream) {
            this.stream = stream;
        }

        public void run() {
            ConsumerIterator<String, String> it = this.stream.iterator();
            while (it.hasNext()) {
                try {
                    String message = it.next().message();
                    System.out.println("Consumed message: " + message);
                    // 手动提交
                    consumerConnector.commitOffsets();
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }


    public static void main(String[] args) {
        new ConsumerTest();
    }
}
