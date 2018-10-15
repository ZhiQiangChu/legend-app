package kafka;

import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import scala.collection.JavaConversions;

import java.util.List;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午11:09 18-5-8
 * @Modified By:
 */
public class AdminUtilsTest {

    public static void main(String[] args) {
        String zookeeperConnect = "10.3.2.20:2181,10.3.2.21:2181,10.3.2.22:2181";
        ZkClient zkClient = new ZkClient(
                zookeeperConnect,
                10 * 1000,
                8 * 1000,
                ZKStringSerializer$.MODULE$);

//        Map<String, Properties> configs = JavaConversions.mapAsJavaMap(AdminUtils.fetchAllTopicConfigs(zkClient));
//        configs.forEach((K, V) -> {
//            System.out.println(K);
//        });

        List<String> consumers = JavaConversions.seqAsJavaList(ZkUtils.getChildren(zkClient, "/consumers"));
        consumers.forEach(System.out::println);

        for (String consumer : consumers) {
            if (!ZkUtils.pathExists(zkClient, "/consumers/" + consumer + "/offsets")) {
                continue;
            }
            List<String> topics = JavaConversions.seqAsJavaList(ZkUtils.getChildren(zkClient, "/consumers/" + consumer + "/offsets"));

            for (String topic : topics) {
                List<String> partions = JavaConversions.seqAsJavaList(ZkUtils.getChildren(zkClient, "/consumers/" + consumer + "/offsets/" + topic));
                for (String partiton : partions) {
                    List<String> data = JavaConversions.seqAsJavaList(ZkUtils.getChildren(zkClient, "/consumers/" + consumer + "/offsets/" + topic + "/" + partiton));
                    data.forEach(System.out::println);
                }

            }
        }
//        JavaConversions.seqAsJavaList(ZkUtils.getChildren(zkClient, "/consumers//offsets/myApp/0"));
        List<String> topics = JavaConversions.seqAsJavaList(ZkUtils.getAllTopics(zkClient));
        topics.forEach(System.out::println);
    }
}
