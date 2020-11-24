package Java8Study.stream1;

import Java8Study.lambda1.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 *  一、Stream操作的三个步骤
 *
 *  - 创建Stream
 *
 *  - 中间操作 ： 如果没有终止操作，则不会做任何操作
 *
 *  - 终止操作
 */
public class TestStreamAPI1 {
    //1.创建Stream
    @Test
    public void test1(){
        //1.通过Collection 系列集合提供的stream() 或 parallelStream() 获取串行流和并行流
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中的静态方法 Stream() 获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        //3.通过Stream类中的静态方法 of()创建流
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        //4.创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, x -> x+2);
        stream4.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);

    }

    //2.中间操作

    //构造流操作所需数据
    List<Employee> employees = Arrays.asList(
            new Employee("jerry",18,9999.1, Employee.Status.FREE),
            new Employee("tom",23,1234,Employee.Status.BUSY),
            new Employee("tom",23,1234, Employee.Status.VACATION),
            new Employee("pony",45,12442.1, Employee.Status.FREE),
            new Employee("tony",50,1232, Employee.Status.VACATION)
    );

    /**
     * 筛选与切片 filter
     */
    @Test
    public void test2(){
        //中间操作
        Stream stream = employees.stream().filter(e -> e.getAge()>35);
        /*
        注意，这里如果不执行下面的 forEach循环打印操作，上面的中间操作也不会执行！这就是延迟执行或惰性求值
         */
        //终止操作
        stream.forEach(System.out::println);

    }

    /**
     * 截断流 limit
     */
    @Test
    public void test3(){
        employees.stream().filter(e -> e.getSalary()> 5000).limit(2).forEach(System.out::println);
    }

    /**
     * 跳过元素 skip
     */
    @Test
    public void test4(){
        employees.stream().filter(e -> e.getSalary() > 5000).skip(2).forEach(System.out::println);
    }

    /**
     * 去重 distinct ，通过hashcode 和 equals 进行去重
     */
    @Test
    public void test5(){
        employees.stream().distinct().forEach(System.out::println);
    }

    /**
     * 映射
     * map 接收一个函数，把该函数应用到流中每个元素上产生一个新流
     * flatMap 接收一个函数，将流中的每个值都换成另一个流，然后把所有流连接成一个流，可以理解为扁平化处理
     *  Stream<Stream<?>>  ----->   Stream<?>
     */

    @Test
    public void test6(){
        List<String> list = Arrays.asList("aa","bb","cc","dd");

        list.stream().map(s -> s.toUpperCase()).forEach(System.out::println);

        employees.stream().map(Employee::getName).forEach(System.out::println);

        //注意这里，flatMap和map的区别，flatMap直接将 Steam<Character> 转为了 Character流
        //可以与 addAll和 add 进行比较
        Stream<Stream<Character>> streamStream = list.stream().map(TestStreamAPI1::toCharacterStream);
        Stream<Character> charStream = list.stream().flatMap(TestStreamAPI1::toCharacterStream);

        charStream.forEach(System.out::println);
    }

    //该函数目的是将String转换为Character流
    public static Stream<Character> toCharacterStream(String str){
        List<Character> list = new ArrayList<>();
        for(char c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }

    /**
     * 排序
     * sorted
     * sorted(Comparator cmp )
     */
    @Test
    public void test7(){
        List<String> list = Arrays.asList("cc","dd","bb","aa");

        list.stream().sorted().forEach(System.out::println);

        list.stream().sorted(Comparator.comparingInt(s -> s.charAt(1)));

        employees.stream().sorted(Comparator.comparingInt(Employee::getAge));
    }


    //3.终止操作

    /**
     * 查找与匹配
     * allMatch 都匹配
     * anyMatch 存在一个匹配
     * noneMatch 没有一个匹配
     * findFirst 返回第一个
     * findAny 返回任意一个
     */

    @Test
    public void test8(){
        boolean b1 = employees.stream().allMatch( e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = employees.stream().anyMatch( e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        boolean b3 = employees.stream().noneMatch(e -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);

        Optional<Employee> op = employees.stream()
                                .sorted(Comparator.comparingDouble(Employee::getSalary))
                                .findFirst();
        System.out.println(op.get());

        //查找任意个空闲的，先过滤
        Optional<Employee> op1 = employees.stream()
                                .filter(e -> e.getStatus().equals(Employee.Status.FREE))
                                .findAny();
        System.out.println(op1.get());

    }

    /**
     * count 总数
     * max 最大值
     * min 最小值
     */

    @Test
    public void test9(){
        long count = employees.stream().count();
        System.out.println("The sum of employees is : " +count);

        //取最大年龄
        Optional<Employee> op1 = employees.stream().max(Comparator.comparingInt(Employee::getAge));
        System.out.println(op1.get());

        //取最低薪水
        Optional<Employee> op2 = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(op2.get());

        //获取最小工资
        Optional<Double> op3 = employees.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(op3.get());

    }


}
