package cn.com.dplus.legend.application;

import cn.com.dplus.legend.listener.ApplicationStartup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
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
    public static int ws_port;

    @Value("${websocket.port}")
    public void setWsPort(String wsPort) {
        ws_port = Integer.valueOf(wsPort);
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.addListeners(new ApplicationStartup());
        app.run(args);
    }
}
