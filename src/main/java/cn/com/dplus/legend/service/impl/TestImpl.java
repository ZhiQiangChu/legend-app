package cn.com.dplus.legend.service.impl;

import cn.com.dplus.legend.aop.Event;
import cn.com.dplus.legend.constant.Constants;
import cn.com.dplus.legend.constant.KafkaTopics;
import cn.com.dplus.legend.constant.LegendCode;
import cn.com.dplus.legend.entity.mongo.Size;
import cn.com.dplus.legend.entity.mongo.UserTest;
import cn.com.dplus.legend.kafka.Kafka;
import cn.com.dplus.legend.service.ITest;
import cn.com.dplus.mongodb.SDMongo;
import cn.com.dplus.mongodb.entity.Condition;
import cn.com.dplus.project.constant.Code;
import cn.com.dplus.project.entity.ResponseEntity;
import cn.com.dplus.project.utils.LogUtil;
import cn.com.dplus.project.utils.UUIDUtils;
import cn.com.dplus.redis.SentinelJedisCluster;
import lombok.Cleanup;
import org.mongodb.morphia.Key;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;

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

    @Override
    public ResponseEntity addUser(UserTest user) {
        user.setId(UUIDUtils.getUUID());
        user.setLikes(10);
        user.setTags(Arrays.asList("java", "c++"));
        Size size = new Size(1, 2, "cm");
        user.setSize(size);
        try {
            Key key = SDMongo.save(user);
            return new ResponseEntity(user);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.Error(e.getMessage());
            return new ResponseEntity(LegendCode.ADD_USER_FAILED, LegendCode.ADD_USER_FAILED_MSG);
        }
    }

    @Override
    public ResponseEntity getUser(UserTest user) {
        try {
            List<UserTest> result = SDMongo.find(UserTest.class, new Condition("_id", user.getId()));
            return new ResponseEntity(result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(LegendCode.ADD_USER_FAILED, LegendCode.ADD_USER_FAILED_MSG);
        }
    }

    @Override
    @Event(service = "legend-app", resource = "test-resource", action = "update", eventName = "test-event")
    public ResponseEntity eventDriven() throws Exception {
        if (true)
            throw new Exception("EXCEPTION");
        return new ResponseEntity("ok");
    }
}
