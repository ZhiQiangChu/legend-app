package cn.com.dplus.legend.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

/**
 * @Description: http://www.voidcn.com/article/p-dadmcgzg-bav.html
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午11:18 18-10-23
 * @Modified By:
 */
public class LeaderElection {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String zkConnectStr = "10.3.5.22:2181,10.3.5.23:2181,10.3.5.24:2181";
        ZooKeeper zooKeeper = new ZooKeeper(zkConnectStr, 3000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.err.println("zookeeper watch event " + event);
            }
        });

        // 保证根节点存在

        String root = "/ha";
        if (zooKeeper.exists(root, null) == null) {
            zooKeeper.create(root, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        // 申请做leader
        String prefix = "/ticket-";
        String myVote = zooKeeper.create(root + prefix, new byte[]{}, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.err.println("我的选票是 " + myVote);
        //查看选举结果(id最小的一个是leader)

        checkLeader(zooKeeper, prefix, myVote, root);
        Thread.currentThread().join();
    }

    private static void checkLeader(ZooKeeper zooKeeper, String prefix, String myVote, String root) throws KeeperException, InterruptedException {
        //寻找最小ID
        List<String> allVotes = zooKeeper.getChildren(root, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                try {
                    checkLeader(zooKeeper, prefix, myVote, root);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        if (isLeader(root, prefix, myVote, allVotes)) {
            System.err.println("成功当选领导" + myVote);
        } else {
            System.err.println("当选领导失败,等待下一轮选举" + myVote);
        }
    }

    private static boolean isLeader(String root, String prefix, String myVote, List<String> allVotes) {
        // 寻找最小ID
        String minVotePath = allVotes.get(0);
        String minVote = fixForString(prefix, minVotePath);
        for (String vote : allVotes) {
            String thisVoteId = fixForString(prefix, vote);
            if (thisVoteId.compareTo(minVote) < 0) {
                minVotePath = vote;
                minVote = thisVoteId;
            }
        }

        System.err.println("当前领导 " + minVotePath);
        return myVote.equals(root + "/" + minVotePath);
    }

    private static String fixForString(String prefix, String str) {
        int index = str.lastIndexOf(prefix);
        if (index >= 0) {
            index += prefix.length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;
    }


}
