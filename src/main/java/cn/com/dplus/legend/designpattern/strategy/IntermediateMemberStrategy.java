package cn.com.dplus.legend.designpattern.strategy;

/**
 * @Description: 中级会员折扣类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:55 18-4-10
 * @Modified By:
 */
public class IntermediateMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.9;
    }
}
