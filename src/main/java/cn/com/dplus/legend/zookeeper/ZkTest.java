package cn.com.dplus.legend.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:26 18-10-23
 * @Modified By:
 */
public class ZkTest {

    private static final int SESSION_TIMEOUT = 30 * 1000;

    private ZooKeeper zooKeeper;

    private Watcher wh = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.println("WacheredEvent >>> " + watchedEvent.toString());

        }
    };

    private void createZKInstance() throws IOException {
        zooKeeper = new ZooKeeper("10.3.5.22:2181,10.3.5.23:2181,10.3.5.24:2181", SESSION_TIMEOUT, this.wh);
    }


    private void createOperation() throws KeeperException, InterruptedException {

        System.out.println("\n1. 创建 ZooKeeper 节点 (znode ： zoo2, 数据： myData2 ，权限： OPEN_ACL_UNSAFE ，节点类型： Persistent");
        zooKeeper.create("/zoo2", "myData2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("\n2. 查看是否创建成功");
        // 添加watcher
        System.out.println(new String(zooKeeper.getData("/zoo2", this.wh, null)));
        // 前面一行我们添加了对/zoo2节点的监视,所以这里对/zoo2进行修改的时候会触发wacher事件
        System.out.println("\n3. 修改节点数据");
        zooKeeper.setData("/zoo2", "zhanjz".getBytes(), -1);
        // 这里再次进行修改,则不会触发watch事件,这就是我们验证zk的一个特性"一次性触发",也就说设置一次
        System.out.println("\n3-1. 再次修改节点数据");
        zooKeeper.setData("/zoo2", "zhanjz-123".getBytes(), -1);
        System.out.println("\n4. 查看是否修改成功");
        System.out.println(new String(zooKeeper.getData("/zoo2", false, null)));

        System.out.println("\n5. 删除节点");
        zooKeeper.delete("/zoo2", -1);

        System.out.println("\n6. 查看节点是否被删除");
        System.out.println("节点状态: [" + zooKeeper.exists("/zoo2", false) + "]");

    }

    private void zkClose() throws InterruptedException {
        zooKeeper.close();
    }

    public static void main(String[] args) throws Exception {
        ZkTest zkTest = new ZkTest();
        zkTest.createZKInstance();
        zkTest.createOperation();
        zkTest.zkClose();
    }
}
