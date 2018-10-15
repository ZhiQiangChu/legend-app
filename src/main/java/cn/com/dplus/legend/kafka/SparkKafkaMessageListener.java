package cn.com.dplus.legend.kafka;

import cn.com.dplus.kafka.listener.MsgListener;
import cn.com.dplus.project.utils.LogUtil;
import org.springframework.stereotype.Component;

/**
 * @ClassName: SparkKafkaMessageListener
 * @Description: TODO
 * @Author 詹军政|zhanjz@sondon.net
 * @Date 17-9-4 上午10:11
 */
@Component
public class SparkKafkaMessageListener implements MsgListener {


    @Override
    public void onMessage(String topic, String data) {
        if (topic.equalsIgnoreCase("DPLUS_DATA_CHANGE_EVENT")) {
            LogUtil.Info(data);
        } else if (topic.equalsIgnoreCase("detect_req") || topic.equalsIgnoreCase("detect_resp")) {
            LogUtil.Info(data);
        }
    }
}

