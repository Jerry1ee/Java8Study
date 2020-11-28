package Java8Study.date1;

import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class TestLocalDateTime {
    //1.LocalDate , LocalDateTime , LocalDateTime
    //使用方式都一样，只是表示的东西不太相同

    @Test
    public void test1(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2020,12,28,13,13,13);
        System.out.println(ldt2);

        //plus加年份
        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);
        //minus减年份
        LocalDateTime ldt4 = ldt.minusYears(2);
        System.out.println(ldt4);

        //拿到年月日
        ldt.getYear();
        ldt.getMonthValue();
        ldt.getDayOfMonth();
        ldt.getHour();
        ldt.getMinute();
        ldt.getSecond();

    }
    //2.Instance 时间戳： 以Unix元年 1970-1-1 00:00:00 到某个时间的毫秒值，称为时间戳
    @Test
    public void test2(){
        Instant instant1 = Instant.now();   //默认获取 UTC时区
        System.out.println(instant1);

        //使用 atOffSet偏移8小时
        OffsetDateTime odt = instant1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(odt);

        //以毫秒展示
        System.out.println(instant1.toEpochMilli());

        //将 元年时间进行偏移
        Instant instant2 = Instant.ofEpochSecond(60);
        System.out.println(instant2);

    }

    //3. Duration 计算两个时间之间的间隔
    //   Period 计算两个日期之间的间隔

    @Test
    public void test3() throws InterruptedException {
        Instant instant1 = Instant.now();
//
//        Thread.sleep(1000);

        Instant instant2 = Instant.now();
        Duration duration = Duration.between(instant1,instant2);
        System.out.println(duration.toMillis());

        LocalTime lt1 = LocalTime.now();

//        Thread.sleep(1000);

        LocalTime lt2 = LocalTime.now();

        System.out.println(Duration.between(lt1, lt2).toMillis());

        //获取日期间隔

        LocalDate localDate1 = LocalDate.of(2015, 1,1);
        LocalDate localDate2 = LocalDate.now();

        Period period = Period.between(localDate1, localDate2);
        System.out.println("相隔几年？");
        System.out.println(period.getYears());
        System.out.println("相隔几月？");
        System.out.println(period.getMonths());
        System.out.println("相隔几天？");
        System.out.println(period.getDays());
    }

    //4.TemporalAdjuster 时间矫正器
    @Test
    public void test4(){
        LocalDateTime ldt = LocalDateTime.now();

        //将 月份指定为 2
        ldt.withMonth(2);

        //TemporalAdjusters 为 TemporalAdjuster 提供了很多静态的实现方法，可以直接调用
        ldt.with(TemporalAdjusters.firstDayOfMonth());

        //自定义 ： 下一个生日

    }
}
