package cn.com.dplus.legend.service.impl;

import cn.com.dplus.legend.constant.Constants;
import cn.com.dplus.legend.constant.KafkaTopics;
import cn.com.dplus.legend.constant.LegendCode;
import cn.com.dplus.legend.kafka.Kafka;
import cn.com.dplus.legend.service.ITest;
import cn.com.dplus.project.constant.Code;
import cn.com.dplus.project.entity.ResponseEntity;
import cn.com.dplus.project.utils.LogUtil;
import cn.com.dplus.redis.SentinelJedisCluster;
import lombok.Cleanup;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import sun.rmi.runtime.Log;

import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午1:47 17-7-4
 * @Modified By:
 */
@Service("test")
public class TestImpl implements ITest {

    @Override
    public ResponseEntity testAop() {
        LogUtil.Info("execute aop");
        return new ResponseEntity("ok");
    }

    @Override
    public ResponseEntity redisTest(String content) {
        try {
            @Cleanup Jedis jedis = SentinelJedisCluster.getJedis();
            SentinelJedisCluster.saveString(jedis, Constants.REDIS_KEY_TEST, content);
            SentinelJedisCluster.setExpireTime(jedis, Constants.REDIS_KEY_TEST, Constants.SECS_1_MINUTES);
            return new ResponseEntity(content);
        } catch (Exception e) {
            LogUtil.Error(e.getMessage());
            return new ResponseEntity(Code.NO_RESULT);
        }
    }

    @Override
    public ResponseEntity publishMsg(String content) {
        try {
            if (StringUtils.isEmpty(content)) {
                return new ResponseEntity(LegendCode.MSG_CONTENT_NULL, LegendCode.MSG_CONTENT_NULL_MSG);
            }
            Kafka.producer.publish(KafkaTopics.LEGEND_MSG_TOPIC, content);
            return new ResponseEntity(content);
        } catch (Exception e) {
            LogUtil.Error(e.getMessage());
            return new ResponseEntity(LegendCode.PUBLISH_FAILED, LegendCode.PUBLISH_FAILED_MSG);
        }
    }
}
