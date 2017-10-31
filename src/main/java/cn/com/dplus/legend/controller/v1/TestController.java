package cn.com.dplus.legend.controller.v1;


import cn.com.dplus.legend.aop.WebLog;
import cn.com.dplus.legend.entity.mongo.UserTest;
import cn.com.dplus.legend.service.ITest;
import cn.com.dplus.project.annotation.ParamsValid;
import cn.com.dplus.project.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午1:42 17-7-4
 * @Modified By:
 */
@RestController
public class TestController extends V1Controller {
    @Autowired
    @Qualifier("test")
    ITest testService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @WebLog
    public ResponseEntity aopTest() {
        return testService.testAop();
    }


    @RequestMapping(value = "redis", method = RequestMethod.POST)
    public ResponseEntity redisTest(String content) {
        return testService.redisTest(content);
    }


    @RequestMapping(value = "kafka", method = RequestMethod.POST)
    public ResponseEntity publishMsg(@ParamsValid(notNull = true) String content) {
        return testService.publishMsg(content);
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity addUser(@ParamsValid(isEntity = true) UserTest user) {
        return testService.addUser(user);
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ResponseEntity getUser(@ParamsValid(isEntity = true) UserTest user) {
        return testService.getUser(user);
    }

    @RequestMapping(value = "event", method = RequestMethod.POST)
    public ResponseEntity eventDriven() throws Exception {
        return testService.eventDriven();
    }
}
