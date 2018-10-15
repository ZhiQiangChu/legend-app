package kafka;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.*;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:07 18-5-9
 * @Modified By:
 */
public class CuratorFrameworkTest {

    private static CuratorFramework client;
    private final static String zookeeperConnect = "10.3.2.20:2181,10.3.2.21:2181,10.3.2.22:2181";

    static {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient(zookeeperConnect, retryPolicy);
        client.start();
    }

    public static List<KafkaConsumerGroupMetadata> getActiveRegularConsumersAndTopics() throws Exception {
        List<KafkaConsumerGroupMetadata> kafkaConsumerGroupMetadataList = new ArrayList<KafkaConsumerGroupMetadata>();
        Set<String> consumerGroups = new HashSet<String>((client.getChildren().forPath("/consumers")));
        for (String consumerGroup : consumerGroups) {
            if (client.checkExists().forPath("/consumers/" + consumerGroup + "/offsets") != null) {
                List<String> topics = client.getChildren().forPath("/consumers/" + consumerGroup + "/offsets");
                for (String topic : topics) {
                    List<String> partitions = client.getChildren()
                            .forPath("/consumers/" + consumerGroup + "/offsets/" + topic);
                    Map<String, Long> partitionOffsetMap = new HashMap<String, Long>();
                    for (String partition : partitions) {
                        byte[] data = client.getData()
                                .forPath("/consumers/" + consumerGroup + "/offsets/" + topic + "/" + partition);
                        if (data != null) {
                            long offset = Long.parseLong(new String(data));
                            partitionOffsetMap.put(partition, offset);
                        }
                    }
                    KafkaConsumerGroupMetadata kafkaConsumerGroupMetadata = new KafkaConsumerGroupMetadata(
                            consumerGroup, topic, partitionOffsetMap);
                    kafkaConsumerGroupMetadataList.add(kafkaConsumerGroupMetadata);
                }
            }
        }
        return kafkaConsumerGroupMetadataList;
    }

    public static List<String> getTopics() throws Exception {
        return client.getChildren().forPath("/brokers/topics");
    }


    public static byte[] getData(String path) throws Exception {
        return client.getData().forPath(path);
    }


    public static void main(String[] args) {
//        try {
//            List<String> topics = getTopics();
//            topics.forEach(System.out::println);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            List<KafkaConsumerGroupMetadata> activeRegularConsumersAndTopics = getActiveRegularConsumersAndTopics();
            System.out.println(activeRegularConsumersAndTopics);
        } catch (Exception e) {

        }
    }
}
