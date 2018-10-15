package cn.com.dplus.legend.designpattern.chainofresponsibility;

/**
 * @Description: Android程序猿类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:22 18-4-10
 * @Modified By:
 */
public class AndroidApe extends ProgramApe {


    private int expenses;
    private String apply = "爹要点钱出差";


    public AndroidApe(int expenses) {
        this.expenses = expenses;
    }

    @Override
    public int getExpenses() {
        return expenses;
    }

    @Override
    public String getApply() {
        return apply + "[" + expenses + "]";
    }
}
