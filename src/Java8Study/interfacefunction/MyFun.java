package Java8Study.interfacefunction;

public interface MyFun {
    //使用default修饰 默认方法
    default String getName(){
        return "Jerry";
    }
}
