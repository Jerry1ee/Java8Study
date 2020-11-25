package Java8Study.interfacefunction;

public interface MyFun {
    //使用default修饰 默认方法
    default String getName(){
        return "Interface 方法 哈哈哈";
    }

    //静态方法用法和普通的差不多
    public static void show(){
        System.out.println("接口中的静态方法");
    }

}
