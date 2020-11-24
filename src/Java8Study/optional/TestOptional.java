package Java8Study.optional;

import Java8Study.lambda1.Employee;
import org.junit.Test;

import java.util.Optional;

public class TestOptional {
    /**
     * Optional 容器类的常用方法
     */

    @Test
    public void test1(){
        //of 封装对象
        Optional<Employee> op = Optional.of(new Employee());
        //get获取对象
        Employee employee = op.get();
        System.out.println(employee);

        //isPresent 判断是否有值
        if(op.isPresent()){
            op.get();
        }

        //orElse(T t) 如果对象有值返回值，没有则返回 T t
        Employee emp = op.orElse(new Employee());
        //此时不用get()获取了，因为返回的直接就是T类对象
        System.out.println(emp);

        //orElseGet(Supplier s) 如果调用对象包含值，返回该值，否则返回 s 获取的值
        //函数式接口
        Optional<Employee> op2 = Optional.ofNullable(null);
        Employee emp2 = op.orElseGet(() -> new Employee());
        System.out.println(emp2);
    }

    //创建空的optional
    @Test
    public void test2(){
        //empty 创建空的实例，即该 op对象是空的
        Optional<Employee> opEmpty = Optional.empty();

        //ofNullable
        Optional<Employee> opOfNullable = Optional.ofNullable(null);
        System.out.println(opOfNullable.get());
    }

    //map(Function f) :如果有值，对其处理并返回处理后的Optional，否则返回empty
    //flatmap

    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(
                new Employee("zhangsan",18,2313.12, Employee.Status.BUSY));
        Optional<String> strOp = op.map( e -> e.getName());
        System.out.println(strOp.get());

        Optional<String> strOp2 = op.flatMap(e -> Optional.of(e.getName()));
        System.out.println(strOp2.get());

    }

}
