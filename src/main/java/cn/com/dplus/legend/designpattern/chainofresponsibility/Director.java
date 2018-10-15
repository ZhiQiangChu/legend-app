package cn.com.dplus.legend.designpattern.chainofresponsibility;

/**
 * @Description: 项目主管类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:49 18-4-10
 * @Modified By:
 */
public class Director extends Leader {

    public Director() {
        super(5000);
    }

    @Override
    protected void reply(ProgramApe ape) {
        System.out.println(ape.getApply());
        System.out.println("Director: Of course Yes!");
    }

}
