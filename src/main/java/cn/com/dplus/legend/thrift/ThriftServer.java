package cn.com.dplus.legend.thrift;

import cn.com.dplus.legend.controller.JazzRpcController;
import cn.com.dplus.legend.thrift.jazz.iface.JazzService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description: Thrift server
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 下午4:51 18-4-10
 * @Modified By:
 */
@Component
public class ThriftServer {

    @Value("${thrift.port}")
    private int port;
    @Value("${thrift.minWorkerThreads}")
    private int minThreads;
    @Value("${thrift.maxWorkerThreads}")
    private int maxThreads;

    private TBinaryProtocol.Factory protocolFactory;
    private TTransportFactory transportFactory;

    @Autowired
    private JazzRpcController jazzRpcController;

    public void init() {
        protocolFactory = new TBinaryProtocol.Factory();
        transportFactory = new TTransportFactory();
    }


    public void start() {
        JazzService.Processor processor = new JazzService.Processor<JazzService.Iface>(jazzRpcController);
        init();
        try {
            TServerTransport transport = new TServerSocket(port);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport);
            tArgs.processor(processor);
            tArgs.protocolFactory(protocolFactory);
            tArgs.transportFactory(transportFactory);
            tArgs.minWorkerThreads(minThreads);
            tArgs.maxWorkerThreads(maxThreads);
            TServer server = new TThreadPoolServer(tArgs);
            System.out.println("thrift服务启动成功, 端口=" + port);
            server.serve();
        } catch (Exception e) {
            System.out.println("thrift服务启动失败");
        }

    }
}
