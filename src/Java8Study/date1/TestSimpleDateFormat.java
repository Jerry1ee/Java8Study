package Java8Study.date1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestSimpleDateFormat {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //使用DateTimeFormatter 代替
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = () -> LocalDate.parse("20201128", dtf);

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> list = new ArrayList<>();

        for(int i =0;i<10;i++){
            list.add(pool.submit(task));
        }
        pool.shutdown();

        for(Future<LocalDate> future : list){
            System.out.println(future.get());
        }
    }
}
