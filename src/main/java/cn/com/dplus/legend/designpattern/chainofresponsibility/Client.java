package cn.com.dplus.legend.designpattern.chainofresponsibility;

/**
 * @Description: 场景模拟类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:55 18-4-10
 * @Modified By:
 */
public class Client {
    public static void main(String[] args) {
        /**
         * 先来一个程序猿 这里给他一个三万以内的随机值表示要申请的差旅费
         */
        ProgramApe ape = new AndroidApe((int) (Math.random() * 30000));
        /**
         * 再来四个老大
         */
        Leader leader = new GroupLeader();
        Leader director = new Director();
        Leader manager = new Manager();
        Leader boss = new Boss();

        /**
         * 设置上级
         */
        leader.setLeader(director);
        director.setLeader(manager);
        manager.setLeader(boss);

        /**
         * 处理申请
         */
        leader.handleRequest(ape);
    }
}
