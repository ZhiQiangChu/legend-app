package cn.com.dplus.legend.kafka;

import cn.com.dplus.kafka.listener.MsgListener;
import cn.com.dplus.legend.constant.KafkaTopics;
import cn.com.dplus.legend.handler.MsgHandler;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:50 17-7-7
 * @Modified By:
 */
@Component
public class KafkaListener implements MsgListener {

    @Override
    public void onMessage(String topic, String data) {
        switch (topic) {
            case KafkaTopics.LEGEND_MSG_TOPIC:
                MsgHandler handler = new MsgHandler();
                handler.publishMsg(data);
                break;
            case KafkaTopics.LEGEND_TEST_TOPIC:
                break;
            default:
                break;
        }
    }
}
