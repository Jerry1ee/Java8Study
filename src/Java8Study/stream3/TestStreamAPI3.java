package Java8Study.stream3;

import Java8Study.lambda1.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TestStreamAPI3 {
    /**
     * 给定一个数字列表，返回由每个数字平方组成的列表
     *  1,2,3,4 -> 1,4,9,16
     */

    @Test
    public void test1(){
        //map做法
        int[] arr ={1,2,3,4};
        Arrays.stream(arr).map(x -> x*x).forEach(System.out::println);
    }
    /**
     * 用map和reduce得到流中有多少employee
     */
    List<Employee> employees = Arrays.asList(
            new Employee("jerry",18,9999.1, Employee.Status.FREE),
            new Employee("tom",23,1000,Employee.Status.BUSY),
            new Employee("amy",26,1234, Employee.Status.VACATION),
            new Employee("pony",45,12442.1, Employee.Status.FREE),
            new Employee("tony",50,1232, Employee.Status.VACATION)
    );

    /**
     *  用map和reduce得到流中有多少employee
     */
    @Test
    public void test2(){
        Optional<Integer> sum = employees.stream().map(e -> 1).reduce(Integer::sum);
        System.out.println(sum);
    }

    /**
     * 找出工作状态为 FREE，且年龄最大的
     */
    @Test
    public void test3(){
        Optional<Employee> freeOldest = employees.stream()
                                                 .filter( e -> e.getStatus().equals(Employee.Status.FREE))
                                                 .max(Comparator.comparingInt(Employee::getAge));
        System.out.println("工作状态为 FREE，且年龄最大的 : "+ freeOldest.get());
    }

    /**
     * 找出
     */

}
