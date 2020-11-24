package Java8Study.lambda2;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 *   一、Java8的基础语法
 *
 *   - 新引入了 箭头操作符 "->"
 *   - 将Lambda表达式拆分为左右两部分
 *     - 左侧： Lambda表达式的参数列表
 *     - 右侧： Lambda表达式中所要执行的功能，也称为 Lambda 体，即也是对接口中方法的实现
 *
 *   - 疑问：如果接口中有多个方法，怎么确定实现的是哪个方法？
 *      - lambda表达式针对的是函数式接口（一个接口中只有一个方法的接口）
 *
 *   --------------------------------------------------------------------------------
 *   - 语法格式：
 *      - 无参数，无返回值： () -> System.out.println("hello!");
 *
 *      - 有一个参数，无返回值：(x) -> System.out.println(x);
 *          - 如果只有一个参数，可以省略小括号
 *
 *      - 有两个以上参数，Lambda体 有多条语句，有返回值：
 *          -  (x, y) -> {
 *             //多条执行语句
 *             return 返回值
 *         };
 *
 *      - 有两个以上参数，Lambda体 只有一条语句，有返回值：
 *          // return 和大括号都可以省略
 *          - (x, y) -> 返回值
 *
 *      - Lambda 表达式 参数列表数据类型可以省略不写，JVM编译器可以通过上下文推断出数据类型，即"类型推断"
 */

/**
 *  二、Lambda的函数式接口：
 *      - 接口中只有一个抽象方法时，即为函数式接口
 *      - 可以使用注解 @FunctionalInterface 修饰，可以检查是否为函数式接口
 */
public class TestLambda2 {

    @Test
    /**
     * 无参数，无返回值
     */
    public void test1(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello!");
            }
        };
        r.run();

        System.out.println("------------------------------------------");

        Runnable r1 = () -> System.out.println("hello! Lambda");
        r1.run();
    }

    @Test
    /**
     * 有一个参数，无返回值
     */
    public void test2(){
        Consumer<String> consumer = (x) -> System.out.println(x);
        consumer.accept("hello!");
    }

    @Test
    /**
     * 有两个以上参数，Lambda体 有多条语句，有返回值
     */
    public void test3(){
        Comparator<Integer> cmp = (x, y) -> {
            System.out.println("接口");
            return Integer.compare(x,y);
        };
    }

    @Test
    /**
     * 有两个以上参数，Lambda体只有一条语句，有返回值
     */
    public void test4(){
        //Comparator<Integer> cmp = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> cmp = Integer::compare;
    }

    //需求：对一个数进行运算
    @Test
    public void test5(){
        Integer num = operation(100, x -> x * x);
        System.out.println(num);

        Integer num1 = operation(200, y -> y+200);
        System.out.println(num1);

    }

    public Integer operation(Integer num, MyFunction mf){
        return mf.getValue(num);
    }


}
