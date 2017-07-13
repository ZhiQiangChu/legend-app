package cn.com.dplus.legend.kafka;

import cn.com.dplus.kafka.KConsumer;
import cn.com.dplus.kafka.KProducer;
import cn.com.dplus.project.utils.EnvUtils;
import cn.com.dplus.project.utils.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:Kafka初始化
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:34 17-7-7
 * @Modified By:
 */
@Component
public class Kafka {
    private static String brokerListEnv;

    private static String brokerList;

    private static String zkConnectEnv;

    private static String zkConnect;

    private static String groupId;

    private static String topics;

    @Value("${kafka.broker_list_env}")
    public void setBrokerListEnv(String brokerListEnv) {
        this.brokerListEnv = brokerListEnv;
    }

    @Value("${kafka.broker_list}")
    public void setBrokerList(String brokerList) {
        this.brokerList = brokerList;
    }

    @Value("${kafka.zookeeper_connect_env}")
    public void setZkConnectEnv(String zkConnectEnv) {
        this.zkConnectEnv = zkConnectEnv;
    }

    @Value("${kafka.zookeeper_connect}")
    public void setZkConnect(String zkConnect) {
        this.zkConnect = zkConnect;
    }

    @Value("${kafka.group_id}")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Value("${kafka.topics}")
    public void setTopics(String topics) {
        this.topics = topics;
    }


    public static KProducer producer;

    public static boolean init() {
        try {
            //先初始化消费者,注意topic需要在springboot配置文件配好
            if (new KConsumer().consume(topics, EnvUtils.getVal(zkConnectEnv, zkConnect), groupId, new KafkaListener())) {
                producer = new KProducer(EnvUtils.getVal(brokerListEnv, brokerList)); // 初始化生产者
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.Error("Init Kafka failed : " + e.getMessage());
        }
        return false;
    }
}
