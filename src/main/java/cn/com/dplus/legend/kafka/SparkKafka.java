package cn.com.dplus.legend.kafka;

import cn.com.dplus.kafka.KConsumer;
import cn.com.dplus.kafka.KProducer;
import cn.com.dplus.project.utils.EnvUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SparkKafka
 * @Description: 连接到大数据平台kafka配置
 * @Author 詹军政|zhanjz@sondon.net
 * @Date 17-9-4 上午9:31
 */

@Component
public class SparkKafka {

    /** broker_list 的环境变量 */
    private static String OUT_BROKER_LIST_ENV;

    @Value("${kafka.out_broker_list_env}")
    public void setOUT_BROKER_LIST_ENV(String oUT_BROKER_LIST_ENV) {
        OUT_BROKER_LIST_ENV = oUT_BROKER_LIST_ENV;
    }

    /** broker_list */
    private static String OUT_BROKER_LIST;

    @Value("${kafka.out_broker_list}")
    public void setOUT_BROKER_LIST(String oUT_BROKER_LIST) {
        OUT_BROKER_LIST = oUT_BROKER_LIST;
    }

    /** zookeeper list 环境变量 */
    private static String OUT_ZOOKEEPER_CONNECT_ENV;

    @Value("${kafka.out_zookeeper_connect_env}")
    public void setOUT_ZOOKEEPER_CONNECT_ENV(String oUT_ZOOKEEPER_CONNECT_ENV) {
        OUT_ZOOKEEPER_CONNECT_ENV = oUT_ZOOKEEPER_CONNECT_ENV;
    }

    /** zookeeper list */
    private static String OUT_ZOOKEEPER_CONNECT;

    @Value("${kafka.out_zookeeper_connect}")
    public void setOUT_ZOOKEEPER_CONNECT(String oUT_ZOOKEEPER_CONNECT) {
        OUT_ZOOKEEPER_CONNECT = oUT_ZOOKEEPER_CONNECT;
    }

    /** 消费者的组id */
    private static String OUT_GROUP_ID;

    @Value("${kafka.out_group_id}")
    public void setOUT_GROUP_ID(String oUT_GROUP_ID) {
        OUT_GROUP_ID = oUT_GROUP_ID;
    }

    /** 这个topis 的格式是 topic:partition */
    private static String OUT_TOPICS;

    @Value("${kafka.out_topics}")
    public void setOUT_TOPICS(String oUT_TOPICS) {
        OUT_TOPICS = oUT_TOPICS;
    }


    /** 外部环境的kafka生成者 */
    public static KProducer producer;

    public static boolean init() {
        try {

            // 初始化计算服务的生产者
            producer = new KProducer(EnvUtils.getVal(OUT_BROKER_LIST_ENV, OUT_BROKER_LIST));

            // 初始化spark消费者并注册监听
            return (new KConsumer().consume(OUT_TOPICS, EnvUtils.getVal(OUT_ZOOKEEPER_CONNECT_ENV, OUT_ZOOKEEPER_CONNECT), OUT_GROUP_ID, new SparkKafkaMessageListener()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
