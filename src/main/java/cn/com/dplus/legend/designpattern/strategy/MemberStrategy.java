package cn.com.dplus.legend.designpattern.strategy;

/**
 * @Description: 抽象折扣类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:52 18-4-10
 * @Modified By:
 */
public interface MemberStrategy {
    /**
     * 计算图书的价格
     *
     * @param booksPrice
     * @return
     */
    double calcPrice(double booksPrice);
}
