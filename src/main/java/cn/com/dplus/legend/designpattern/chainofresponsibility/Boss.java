package cn.com.dplus.legend.designpattern.chainofresponsibility;

/**
 * @Description: 老总类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:53 18-4-10
 * @Modified By:
 */
public class Boss extends Leader {

    public Boss() {
        super(40000);
    }

    @Override
    protected void reply(ProgramApe ape) {
        System.out.println(ape.getApply());
        System.out.println("Boss: Of course Yes!");
    }
}
