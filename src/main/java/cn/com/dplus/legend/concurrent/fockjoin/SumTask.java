package cn.com.dplus.legend.concurrent.fockjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @Description: 大任务：计算随机的100个数字的和
 * 小任务：每次只能计算20个数值的和
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午10:15 18-4-12
 * @Modified By:
 */
public class SumTask extends RecursiveTask<Integer> {
    /**
     * 每个小任务最多只能计算20个数值的和
     */
    private static final int MAX = 20;

    /**
     * 随机数组
     */
    private int[] arr;

    /**
     * 起点
     */
    private int start;

    /**
     * 终点
     */
    private int end;

    /**
     * 构造方法
     *
     * @param arr
     * @param start
     * @param end
     */
    public SumTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < MAX) {
            for (int i = start; i < end; i++) {
                sum += arr[i];
            }
            return sum;
        } else {
            // 分拆成两个任务
//            int middle = (end + start) / 2;
            int middle = (start + end) >>> 1;
            SumTask left = new SumTask(arr, start, middle);
            SumTask right = new SumTask(arr, middle, end);
            // 并行执行两个小任务
            left.fork();
            right.fork();
            // 把两个小任务累加的结果合并起来
            return left.join() + right.join();
        }
    }

    public static void main(String[] args) throws Exception {
        int[] arr = new int[100];
        Random random = new Random();
        int total = 0;
        // 初始化100个数字元素
        for (int i = 0; i < 100; i++) {
            arr[i] = random.nextInt(100);
            total += arr[i];
        }
        System.out.println("初始化时的总和：" + total);
        // 创建线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask task = new SumTask(arr, 0, arr.length);
        // 提交可分解的计算任务
        Future<Integer> future = forkJoinPool.submit(task);
        // 获取最终计算结果
        System.out.println("计算出来的总和：" + future.get());
        // 关闭线程池
        forkJoinPool.shutdown();
    }
}
