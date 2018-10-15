package cn.com.dplus.legend.concurrent.fockjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 大任务：打印0-200的数值
 * 小任务：每次只能打印50个数值
 * <p>
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午9:55 18-4-12
 * @Modified By:
 */
public class PrintTask extends RecursiveAction {
    /**
     * 每个小任务最多只能打印50个数
     */
    private static final int MAX = 50;

    /**
     * 起点
     */
    private int start;

    /**
     * 终点
     */
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "的值:" + i);
            }
        } else {
            //将大任务分解成两个小任务
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();
        }
    }


    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 提交可分解的PrintTask任务
        forkJoinPool.submit(new PrintTask(0, 200));
        // 阻塞到当前线程知道FockJoinPool中所有的任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        // 关闭线程池
        forkJoinPool.shutdown();
    }
}


