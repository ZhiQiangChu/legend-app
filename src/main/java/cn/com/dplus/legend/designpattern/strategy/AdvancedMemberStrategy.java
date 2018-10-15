package cn.com.dplus.legend.designpattern.strategy;

import cn.com.dplus.legend.designpattern.strategy.MemberStrategy;

/**
 * @Description: 高级会员折扣类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:57 18-4-10
 * @Modified By:
 */
public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
    }
}
