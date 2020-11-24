package Java8Study.parallel;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {
    @Test
    public void test1(){

        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0,10000000000L);
        Long sum =  pool.invoke(task);

        Instant end = Instant.now();
        System.out.println("ForkJoin计算 1-10000000000 耗费时间为 "+Duration.between(start,end).toMillis()+"ms");

        long sum2 = 0l;

        Instant start1 = Instant.now();
        for(long i = 1;i<=10000000000l;i++){
            sum2 +=i;
        }
        Instant end1 = Instant.now();
        System.out.println("普通for循环计算 1-10000000000 耗费时间为"+Duration.between(start1,end1).toMillis()+"ms");
    }


    /**
     * Java8 并行流
     * 使用parallel 切换并行
     * 使用sequential 切换串行
     */
    @Test
    public void test2(){

        Instant start = Instant.now();
        LongStream.rangeClosed(0,10000000000l)
                  .parallel()       //并行流
                  .reduce(0,Long::sum);
        Instant end = Instant.now();
        System.out.println("Java8 parallel 计算 1-1000000000 耗时为 "+ Duration.between(start,end).toMillis()+ "ms");

    }

}
