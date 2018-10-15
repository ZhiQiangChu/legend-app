package cn.com.dplus.legend.designpattern.chainofresponsibility;

/**
 * @Description: 领导抽象类
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:35 18-4-10
 * @Modified By:
 */
public abstract class Leader {

    /**
     * 当前领导能批复的金额
     */
    private int expenses;

    /**
     * 上级领导
     */
    private Leader mSuperiorLeader;


    /**
     * 含参构造方法
     *
     * @param expenses 当前领导能批复的金额
     */
    public Leader(int expenses) {
        this.expenses = expenses;
    }

    protected abstract void reply(ProgramApe ape);


    /**
     * 处理请求
     *
     * @param ape 具体的程序猿
     */
    public void handleRequest(ProgramApe ape) {

        /**
         * 如果说程序猿申请的money在当前领导的批复范围内
         */
        if (ape.getExpenses() <= expenses) {
            reply(ape);
        } else {
            /**
             * 否则看看当前领导有没有上级
             */
            if (mSuperiorLeader != null) {
                /**
                 * 有的话简单抛给上级处理即可
                 */
                mSuperiorLeader.handleRequest(ape);
            } else {
                /**
                 * 没有的话就批复不了
                 */
                System.out.println("Goodbye my money...");
            }
        }
    }

    /**
     * 为当前领导设置一个上级程序猿
     *
     * @param superiorLeader 上级领导
     */
    public void setLeader(Leader superiorLeader) {
        this.mSuperiorLeader = superiorLeader;
    }
}
