package cn.com.dplus.legend.controller;

import cn.com.dplus.legend.thrift.jazz.iface.JazzService;
import org.apache.thrift.TException;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午5:00 18-4-10
 * @Modified By:
 */
@Controller
@EnableCaching(proxyTargetClass = true)
public class JazzRpcController implements JazzService.Iface {

    @Override
    public boolean exists(String path) throws TException {
        return false;
    }
}
