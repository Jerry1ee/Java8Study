package Java8Study.method1;

import Java8Study.lambda1.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用： 若lambda体中的内容有方法已经实现，可以使用方法引用
 *          - 可以理解为方法引用是lambda表达式的另外一种表达形式
 *
 *  ** 注意：要实现的抽象方法的参数列表和返回类型要与 引用的方法 完全一致
 *
 *  主要有三种语法格式：
 *
 *  对象::实例方法名
 *
 *  类::静态方法名
 *
 *  类::实例方法名
 *
 * 二、构造器引用：
 *
 * ClassName::new
 *
 * 三、数组引用
 *
 * Type::new
 */
public class TestMethodRef {

    //对象::实例方法名
    @Test
    public void test1(){
        //发现 System.out.println() 是已有方法
        Consumer<String> consumer = x -> System.out.println(x);

        //只用用现有方法充当lambda表达式中的方法体，即方法引用
        Consumer<String> consumer1 = System.out::println;

        consumer.accept("hello");
        consumer1.accept("world!");
    }

    @Test
    public void test2(){
        Employee employee = new Employee();
        Supplier<String> supplier = employee::getName;
        System.out.println(supplier.get());

        Supplier<Integer> supplier1 = employee::getAge;
        System.out.println(supplier1.get());
    }

    //类::静态方法
    @Test
    public void test3(){
        //Comparator<Integer> comparator = (x, y) -> Integer.compare(x,y);
        //                          |
        Comparator<Integer> comparator = Integer::compare;
    }

    //类::实例方法名
    //只有当第一个参数是方法调用者，第二个参数是方法参数时才能使用
    @Test
    public void test4(){
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String, String> bp1 = String::equals;
    }

    //构造器引用
    @Test
    public void test5(){
        Supplier<Employee> supplier = () -> new Employee();

        //由于 Supplier 接口的方法是无参的，所以这里调用的 构造器也是 无参构造器
        Supplier<Employee> supplier1 = Employee::new;

        Employee employee = supplier1.get();
        System.out.println("Supplier 无参构造器："+employee);
        System.out.println();

        //Function接口有一个参数，这里调用的构造器是一个参数的
        Function<Integer, Employee> employeeWithInteger = Employee::new;
        Employee employee1 = employeeWithInteger.apply(18);
        System.out.println("Function 有一个参数的构造器：" + employee1);
    }

    //数组引用

    @Test
    public void test6(){
        Function<Integer,String[]> function = (x) -> new String[x];

        Function<Integer,String[]> function1 = String[]::new;

    }

}
