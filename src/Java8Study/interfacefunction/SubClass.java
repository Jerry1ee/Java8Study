package Java8Study.interfacefunction;

//public class SubClass extends MyClass implements MyFun, MyFun1{
//
//}
//如果多个实现的话，要指定同名方法究竟是实现的哪个接口
public class SubClass implements MyFun, MyFun1{

    @Override
    public String getName() {
        return MyFun.super.getName();
    }
}
