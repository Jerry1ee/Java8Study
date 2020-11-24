package Java8Study.lambda1;

import org.junit.Test;

import java.util.*;

public class TestLambda {

    //原来的匿名内部类
    @Test
    public void test1(){
        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        //可以在其他集合类中使用新定义的cmp比较器了
        TreeSet<Integer> set = new TreeSet<>(cmp);
    }

    //Lambda 表达式
    @Test
    public void test2(){
        Comparator<Integer> cmp = (x, y) ->Integer.compare(x, y);
        TreeSet<Integer> set = new TreeSet<>(cmp);
    }


    List<Employee> employees = Arrays.asList(
            new Employee("jerry",18,9999.1),
            new Employee("tom",23,1234),
            new Employee("jack",38,4214),
            new Employee("pony",45,12442.1),
            new Employee("tony",50,1232)
    );

    //下面来看几个需求：


    //需求：获取公司中员工年龄大于35的所有员工信息
    public List<Employee> filterEmployeesByAge(List<Employee> employees){
        List<Employee> filterEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if(employee.getAge()>35){
                filterEmployees.add(employee);
            }
        }
        return filterEmployees;
    }

    //需求：获取公司中员工工资大于 5000 的员工信息
    public List<Employee> filterEmployeesBySalary(List<Employee> employees){
        List<Employee> filterEmployees = new ArrayList<>();
        for(Employee employee : employees) {
            if(employee.getSalary()>5000){
                filterEmployees.add(employee);
            }
        }
        return filterEmployees;
    }

    //测试
    @Test
    public void test3(){
        List<Employee> filterEmployees =  filterEmployeesByAge(employees);
        for(Employee employee : filterEmployees){
            System.out.println(employee);
        }
    }
    //  ---------------------------------------------
    // ｜以上的问题：需求的实现方式重复较多，造成大段代码重复 ｜
    //  ---------------------------------------------

    //解决1：采用实现接口 MyPredicate的方式，有几种过滤方法就实现几次接口，即策略模式

    //需求：获取公司中员工年龄大于35的所有员工信息
    public List<Employee> filterEmployeesByAge(List<Employee> employees, MyPredicate<Employee> myPredicate){
        List<Employee> filterEmployees = new ArrayList<>();
        for(Employee employee : employees){
            if(myPredicate.test(employee)){
                filterEmployees.add(employee);
            }
        }
        return filterEmployees;
    }

    @Test
    public void test4(){
        List<Employee> filterEmployeesByAge = filterEmployeesByAge(employees, new AgeFilter());
        System.out.println("Filter By Age:");
        for(Employee employee : filterEmployeesByAge){
            System.out.println(employee);
        }

        System.out.println("-----------------------------------------------------------------");

        System.out.println("Filter By Salary:");
        List<Employee> filterEmployeesBySalary = filterEmployeesByAge(employees, new SalaryFilter());
        for(Employee employee : filterEmployeesBySalary){
            System.out.println(employee);
        }
    }
    //实现时只需要一种方法，即只需要一个employee列表和一个过滤器对象即可，但是实现类可能会过多
    //解决2：匿名内部类
    @Test
    public void test5(){
        List<Employee> filterEmployeesByAge = filterEmployeesByAge(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge()>35;
            }
        });
        System.out.println("Filter By Age:");
        for(Employee employee : filterEmployeesByAge){
            System.out.println(employee);
        }
    }
    //解决3：lambda表达式
    @Test
    public void test6(){
        List<Employee> filterEmployeesByAge = filterEmployeesByAge(employees, e -> e.getAge() >35);
        filterEmployeesByAge.forEach(System.out::println);
    }

    //解决4：以上接口，实现类都不存在时，使用Stream API
    @Test
    public void test7(){
        //所有年龄大于35的
        employees.stream()
                .filter(e -> e.getAge() >35)
                .forEach(System.out::println);
        //所有薪资大于5000的
        employees.stream()
                .filter(e -> e.getSalary() > 5000)
                .forEach(System.out::println);
        //打印出每个对象的名字
        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

    }

}
