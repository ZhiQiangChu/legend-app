package cn.com.dplus.legend.designpattern.chainofresponsibility;

/**
 * @Description: 部门经理类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:51 18-4-10
 * @Modified By:
 */
public class Manager extends Leader {

    public Manager() {
        super(10000);
    }

    @Override
    protected void reply(ProgramApe ape) {
        System.out.println(ape.getApply());
        System.out.println("Manager: Of course Yes!");
    }
}
