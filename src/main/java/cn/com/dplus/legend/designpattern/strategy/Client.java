package cn.com.dplus.legend.designpattern.strategy;

/**
 * @Description: 客户端
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午11:01 18-4-10
 * @Modified By:
 */
public class Client {

    public static void main(String[] args) {
        // 选择并创建需要使用的策略对象
        MemberStrategy strategy = new IntermediateMemberStrategy();
        // 创建环境
        Price price = new Price(strategy);
        // 计算价格
        double quote = price.quote(300);
        System.out.println("图书的最终价格为:" + quote);
    }
}
