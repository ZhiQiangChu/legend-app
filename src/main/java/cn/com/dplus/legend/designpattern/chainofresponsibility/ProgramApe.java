package cn.com.dplus.legend.designpattern.chainofresponsibility;

/**
 * @Description: 程序猿抽象接口
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:20 18-4-10
 * @Modified By:
 */
public abstract class ProgramApe {
    /**
     * 获取程序猿具体的差旅费用
     *
     * @return
     */
    public abstract int getExpenses();

    /**
     * 获取茶旅费申请
     *
     * @return
     */
    public abstract String getApply();
}
