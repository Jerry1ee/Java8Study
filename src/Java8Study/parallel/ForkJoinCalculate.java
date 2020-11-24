package Java8Study.parallel;

import java.util.concurrent.RecursiveTask;

/**
 * 使用ForkJoin 并行处理大数据
 * 注意：
 * - 拆分粒度 THRESHOLD 不能太小，否则拆分操作的耗时占用很多
 * - 数据量很大时才有明显提升
 *
 * Java8 中使用更加方便，其实Java8中的 parallel原理也是ForkJoin
 *
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    private long start;
    private long end;

    //拆分的最小粒度
    private static final long THRESHOLD = 10000;

    @Override
    protected Long compute() {
        long length = end - start;
        if(length <= THRESHOLD){
            long sum = 0;
            for(long i =start;i<=end;i++){
                sum += i;
            }
            return sum;
        }else{
            long mid = (start + end) /2;
            ForkJoinCalculate left = new ForkJoinCalculate(start,mid);
            //拆分子任务，同时压入线程队列
            left.fork();

            ForkJoinCalculate right = new ForkJoinCalculate(mid+1, end);
            right.fork();

            return left.join() + right.join();
        }
    }
}
