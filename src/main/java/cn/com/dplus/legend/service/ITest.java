package cn.com.dplus.legend.service;


import cn.com.dplus.project.entity.ResponseEntity;

import java.util.Map;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午1:47 17-7-4
 * @Modified By:
 */
public interface ITest {


    ResponseEntity testAop();

    ResponseEntity redisTest(String content);

    ResponseEntity publishMsg(String content);
}
