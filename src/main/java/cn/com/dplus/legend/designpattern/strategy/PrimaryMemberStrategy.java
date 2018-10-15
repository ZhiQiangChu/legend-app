package cn.com.dplus.legend.designpattern.strategy;

/**
 * @Description: 初级会员折扣类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:54 18-4-10
 * @Modified By:
 */
public class PrimaryMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于初级会员的没有折扣");
        return booksPrice;
    }
}
