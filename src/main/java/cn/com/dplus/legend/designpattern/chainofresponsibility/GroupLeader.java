package cn.com.dplus.legend.designpattern.chainofresponsibility;

/**
 * @Description: 小组组长类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:46 18-4-10
 * @Modified By:
 */
public class GroupLeader extends Leader {

    public GroupLeader() {
        super(1000);
    }

    @Override
    protected void reply(ProgramApe ape) {
        System.out.println(ape.getApply());
        System.out.println("GroupLeader: Of course Yes!");
    }
}
