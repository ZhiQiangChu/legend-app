package cn.com.dplus.legend.application;

import cn.com.dplus.legend.thrift.ThriftServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午12:04 17-7-4
 * @Modified By:
 */
@SpringBootApplication
@ComponentScan("cn.com.dplus.*")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Application {

    private static ThriftServer thriftServer;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
//        app.addListeners(new ApplicationStartup());
        ApplicationContext context = app.run(args);
        thriftServer = context.getBean(ThriftServer.class);
        thriftServer.start();
    }
}
