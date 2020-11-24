package Java8Study.lambda3;

import Java8Study.lambda1.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *      对Lambda进行练习
 *
 */
public class TestLambda3 {
    /**
     * 实现 Collections.sort()，排序集合中的元素
     * 先按年龄排，如果年龄一样，按姓名比
     */
    List<Employee> employees = Arrays.asList(
            new Employee("jerry",18,9999.1),
            new Employee("tom",23,1234),
            new Employee("jack",38,4214),
            new Employee("pony",45,12442.1),
            new Employee("tony",50,1232)
    );

    @Test
    public void test1(){
        Collections.sort(employees, (x, y) -> {
            if(x.getAge() == y.getAge()){
                return x.getName().compareTo(y.getName());
            }else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        } );
        employees.forEach(System.out::println);
    }

    @Test
    public void test2(){
        String resString = changeString("jerrylee", x ->  x.toUpperCase().substring(2,5));
        System.out.println(resString);
    }

    public String changeString(String str, MyFunction mf){
        return mf.getValue(str);
    }

    @Test
    public void test3(){
        Long resLong = getSomething(1234l, 2345l, (x, y) -> x+y);

        System.out.println(resLong);
        Long resLong2 = getSomething(1234l, 2345l, (x, y) -> x*y);
        System.out.println(resLong2);
    }

    public Long getSomething(Long l1, Long l2, MyFunctionWithGenerics<Long, Long> mfg){
        return mfg.doSomething(l1, l2);
    }


}
