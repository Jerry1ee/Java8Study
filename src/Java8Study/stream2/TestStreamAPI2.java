package Java8Study.stream2;

import Java8Study.lambda1.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 终止操作
 *
 * 归约 ：
 * reduce(T identity, BinaryOperator) / reduce(BinaryOperator) 可以将流中元素反复结合起来得到一个值
 *
 * 收集 ：
 * collect 将流转换为其他形式，接收一个Collector接口实现
 */
public class TestStreamAPI2 {

    List<Employee> employees = Arrays.asList(
            new Employee("jerry",18,9999.1, Employee.Status.FREE),
            new Employee("tom",23,1234,Employee.Status.BUSY),
            new Employee("tom",23,1234, Employee.Status.VACATION),
            new Employee("pony",45,12442.1, Employee.Status.FREE),
            new Employee("tony",50,1232, Employee.Status.VACATION)
    );

    @Test
    public void test1(){

        //reduce
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //参数为：identity 起始值 和 二元运算
        //先把起始值 0 作为x，流中取出一个作为y
        //再把相加结果作为x，再从流中取出一个作为y ...
        int sum =  list.stream()
                .reduce(0, (x, y) -> x+y);
        System.out.println(sum);

        //计算公司中所有员工工资总和
        Optional<Double> op = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(op.get());

        //collect

        //收集当前公司所有员工姓名并放在一起，放入List中
        List<String> nameList = employees.stream().map(Employee::getName).collect(Collectors.toList());
        nameList.forEach(System.out::println);

        //放入特殊的自定义集合中，使用 Collectors.toCollection()
        Set<String> nameSet = employees.stream()
                .map(Employee::getName).collect(Collectors.toCollection(HashSet::new));

    }

    @Test
    public void test2(){
        //总数
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println("Sum is : "+ count);

        //salary 平均值
        Double average = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("The average salary is :" + average);

        //salary 总和
        Double sum = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("The sum salary is : " + sum);

        //最大或最小值
        Optional<Double> max = employees.stream()
                .map(Employee::getSalary)
                .collect(Collectors.maxBy(Double::compare));
        System.out.println("The max salary is : "+ max);
    }

    @Test
    public void test3(){
        /**
         * 分组 groupingBy
         * 分组的返回结果是 Map形式，Key为分组条件，value为分好的组
         */
        Map<Employee.Status, List<Employee>> map = employees.stream()
                                                            .collect(Collectors.groupingBy(Employee::getStatus));
        //注意 map 的forEach，两个参数
        map.forEach((x, y) ->{
            System.out.println(x);
            y.forEach(System.out::println);
        });

        //多级分组， 先按状态分，再按年龄分

        Map<Employee.Status, Map<String,List<Employee>>> doubleMap = employees.stream()
                 .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                              if (e.getAge() <35){
                                  return "青年";
                              }else if(e.getAge() <= 50){
                                  return "中年";
                              }else {
                                  return "老年";
                              }
                 })));

        doubleMap.forEach((status, stringListMap) -> {
            System.out.println("- 一级分组"+status);
            stringListMap.forEach((ageRange, employees)->{
                System.out.println("    - 二级分组"+ageRange);
                employees.forEach(System.out::println);
            });
        });
    }

    /**
     * 分片 partitioningBy，接收 predicate 形式函数
     * 返回结果依然是 Map 形式
     */

    @Test
    public void test4(){
        Map<Boolean, List<Employee>> map = employees.stream()
                                                    .collect(Collectors.partitioningBy(e -> e.getSalary()>5000));
        map.forEach((bool, employees) -> {
            System.out.println(bool);
            employees.forEach(System.out::println);
        });
    }

    /**
     * SummaryStatistics 其中包含许多刚才提到的操作
     */
    @Test
    public void test5(){
       DoubleSummaryStatistics dss =  employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));

       dss.getAverage();
       dss.getCount();
       dss.getMax();
       dss.getMin();
       dss.getSum();
    }
    /**
     * joining，连接操作
     */
    @Test
    public void test6(){
        String str = employees.stream().map(Employee::getName).collect(Collectors.joining(","));
        System.out.println(str);
    }



}
