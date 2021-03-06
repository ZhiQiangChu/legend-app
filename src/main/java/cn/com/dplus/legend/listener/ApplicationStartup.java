package cn.com.dplus.legend.listener;

import cn.com.dplus.project.utils.LogUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:49 17-7-5
 * @Modified By:
 */
@Configuration
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    private static boolean startCompleted = false;
    ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if (!startCompleted) {
//                Assert.isTrue(Redis.init(), "Redis init failed!");
//                Assert.isTrue(Kafka.init(), "Kafka init failed!");
//                Assert.isTrue(MongoDB.init(), "Mongo init failed!");
//                executor.submit(new HttpRecorder());
//                executor.submit(new WebSocketServer());
//                Assert.isTrue(SparkKafka.init(),"");

                startCompleted = true;
                LogUtil.Info("Application start success!");
            }
        } catch (Throwable t) {
            startCompleted = false;
            LogUtil.Info("Application start failed!");
            System.exit(1);
        }
    }
}
