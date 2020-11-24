package Java8Study.lambda4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 四大核心函数式接口
 *
 * - Consumer<T> : 消费型接口
 *  - void accept(T t);
 *
 * - Supplier<T> : 供给型接口
 *  - T get();
 *
 * - Function<T, R> : 函数型接口
 *  - R apply(T t);
 *
 * - Predicate<T> : 断言型接口
 *  - boolean test(T t);
 *
 *  其他子接口可以参考 ：https://www.runoob.com/java/java8-functional-interfaces.html
 *
 */
public class TestLambda4 {
    // Consumer<T> : 消费型接口
    @Test
    public void test1(){
        consume(10000, m -> System.out.println("消费了"+ m +"元"));
    }
    public void consume(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }

    // Supplier<T> : 供给型接口
    @Test
    public void test2(){
        List<Integer> list = getNumberList(10, () -> (int)(Math.random()*100));
        list.forEach(System.out::println);
    }
    // 产生一些整数，并放入集合中
    public List<Integer> getNumberList(int number, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();

        //将 每次产生的数字放到集合当中
        for(int i =0;i<number;i++){
            list.add(supplier.get());
        }
        return list;
    }

    // Function<T, R> : 函数型接口
    @Test
    public void test3(){
        String resStr = strHandler("处理后应该两边加上书名号", str -> "《"+str + "》");
        System.out.println(resStr);

        String resStr1 = strHandler("\t \t   去除后两边不应该含任何空格和制表符" , str -> str.trim());
        System.out.println(resStr1);
    }

    //需求：用于处理字符串
    public String strHandler(String str, Function<String, String> func){
        return func.apply(str);
    }

    // Predicate<T> : 断言型接口
    @Test
    public void test4(){
        List<String> list = Arrays.asList("Hello", "Goodnight", "hi", "ok","WuHu!");

        List<String> filterList = filterStr(list, str -> str.length()>3);

        filterList.forEach(System.out::println);
    }

    //需求：将满足某条件的字符串添加到集合中
    public List<String> filterStr(List<String> StrList, Predicate<String> predicate){
        List<String> filterList = new ArrayList<>();

        for(String str : StrList){
            if(predicate.test(str)){
                filterList.add(str);
            }
        }
        return filterList;
    }

}
